package com.xd.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.entity.TBlog;
import com.xd.entity.TComment;
import com.xd.entity.TType;
import com.xd.entity.TUser;
import com.xd.entityVO.DetailBlogVo;
import com.xd.entityVO.RecommendBlogVo;
import com.xd.entityVO.TBlogVo;
import com.xd.entityVO.adminShowBlogVo;
import com.xd.mapper.TBlogMapper;
import com.xd.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Malloc
 * @since 2020-07-20
 */
@Service
public class TBlogServiceImpl extends ServiceImpl<TBlogMapper, TBlog> implements TBlogService {

    @Autowired
    TTypeService typeService;
    @Autowired
    TUserService userService;
    @Autowired
    TMessageService messageService;
    @Autowired
    TCommentService commentService;

    @Override
    public Page<TBlogVo> getAllBlog(Page<TBlog> Page){
        QueryWrapper<TBlog> blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper.orderByDesc("create_time");
        blogQueryWrapper.eq("published",1);
        Page<TBlog> blogPage =this.page(Page,blogQueryWrapper);
        List<TBlog> blogList = blogPage.getRecords();
        List<TBlogVo> blogVoList = new ArrayList<>();
        for (TBlog blog : blogList){
            TBlogVo blogVo = new TBlogVo();
            BeanUtil.copyProperties(blog,blogVo);
//            设置blogVo所属类型
            QueryWrapper<TType> typeQueryWrapper = new QueryWrapper<>();
            typeQueryWrapper.eq("id",blog.getTypeId());
            blogVo.setTypeName(this.typeService.getOne(typeQueryWrapper).getName());
//            设置blogVo的作者名
            QueryWrapper<TUser> tUserQueryWrapper = new QueryWrapper<>();
            tUserQueryWrapper.eq("id",blog.getUserId());
            TUser user = this.userService.getOne(tUserQueryWrapper);
            blogVo.setNickName(user.getNickname());
//            设置blogVo的作者头像
            blogVo.setAvatar(user.getAvatar());
            blogVoList.add(blogVo);
        }
        Page<TBlogVo> blogVoPage = new Page<>(blogPage.getCurrent(),10,blogPage.getPages()*10);
        blogVoPage.setRecords(blogVoList);
        return blogVoPage;
    }

    @Override
    public Page<TBlogVo> getSearchBlog(String queryContent) {
        QueryWrapper<TBlog> blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper
                .like("description",queryContent)
                .or()
                .like("title",queryContent);
        List<TBlog> blogList = this.list(blogQueryWrapper);
        List<TBlogVo> blogVoList = new ArrayList<>();
        for (TBlog blog : blogList){
            TBlogVo blogVo = new TBlogVo();
            BeanUtil.copyProperties(blog,blogVo);
//            设置blogVo所属类型
            QueryWrapper<TType> typeQueryWrapper = new QueryWrapper<>();
            typeQueryWrapper.eq("id",blog.getTypeId());
            blogVo.setTypeName(this.typeService.getOne(typeQueryWrapper).getName());
//            设置blogVo的作者名
            QueryWrapper<TUser> tUserQueryWrapper = new QueryWrapper<>();
            tUserQueryWrapper.eq("id",blog.getUserId());
            TUser user = this.userService.getOne(tUserQueryWrapper);
            blogVo.setNickName(user.getNickname());
//            设置blogVo的作者头像
            blogVo.setAvatar(user.getAvatar());
            blogVoList.add(blogVo);
        }
        Page<TBlogVo> blogVoPage = new Page<>(1,1000,blogList.size());
        blogVoPage.setRecords(blogVoList);
        return blogVoPage;
    }

    @Override
    public Page<adminShowBlogVo> adminSearchBlog(RecommendBlogVo searchBlog) {
        System.out.println(searchBlog);
        QueryWrapper<TBlog> blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper.eq("type_id",searchBlog.getTypeId())
                .like("title",searchBlog.getTitle())
                .orderByDesc("create_time");
        List<TBlog> blogList = this.list(blogQueryWrapper);
        List<adminShowBlogVo> blogVoList = new ArrayList<>();
        for (TBlog blog : blogList){
            adminShowBlogVo blogVo = new adminShowBlogVo();
            BeanUtil.copyProperties(blog,blogVo);
//            设置博客文章的类型名称
            QueryWrapper<TType> typeQueryWrapper = new QueryWrapper<>();
            typeQueryWrapper.eq("id",blog.getTypeId());
            TType type = typeService.getOne(typeQueryWrapper);
            blogVo.setTypeName(type.getName());
            blogVoList.add(blogVo);
        }
        Page<adminShowBlogVo> blogVoPage = new Page<>(1,1000,blogList.size());
        blogVoPage.setRecords(blogVoList);
        return blogVoPage;
    }

    @Override
    public List<RecommendBlogVo> getRecommendBlog() {
        QueryWrapper<TBlog> blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper.eq("recommend",true).last("LIMIT 0,4").orderByDesc("create_time");
        List<TBlog> blogList = this.list(blogQueryWrapper);
        List<RecommendBlogVo> reBlogVoList = new ArrayList<>();
        for (TBlog blog : blogList){
            RecommendBlogVo reBlogVo = new RecommendBlogVo();
            BeanUtil.copyProperties(blog,reBlogVo);
            reBlogVoList.add(reBlogVo);
        }
        return reBlogVoList;
    }

    @Override
    public DetailBlogVo getDetailedBlog(Long id) {
        TBlog blog = this.getById(id);
        DetailBlogVo detailBlogVo = new DetailBlogVo();
        BeanUtil.copyProperties(blog,detailBlogVo);
//        内容转换成markdown形式
        detailBlogVo.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
//        设置DetailBlogVo的Type
        QueryWrapper<TType> typeQueryWrapper = new QueryWrapper<>();
        typeQueryWrapper.eq("id",blog.getTypeId());
        detailBlogVo.setTypeName(typeService.getOne(typeQueryWrapper).getName());
//        设置DetailBlogVo的nickName和头像
        QueryWrapper<TUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id",blog.getUserId());
        TUser user = userService.getOne(userQueryWrapper);
        detailBlogVo.setNickName(user.getNickname());
        detailBlogVo.setAvatar(user.getAvatar());
        return detailBlogVo;
    }

    @Override
    public Page<adminShowBlogVo> getAdminShowBlog(Page<TBlog> page) {
        QueryWrapper<TBlog> blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper.orderByDesc("create_time");
        Page<TBlog> Page = this.page(page,blogQueryWrapper);
        List<TBlog> blogList = Page.getRecords();
        List<adminShowBlogVo> blogVoList = new ArrayList<>();
        for (TBlog blog : blogList){
            adminShowBlogVo blogVo = new adminShowBlogVo();
            BeanUtil.copyProperties(blog,blogVo);
//            设置博客文章的类型名称
            QueryWrapper<TType> typeQueryWrapper = new QueryWrapper<>();
            typeQueryWrapper.eq("id",blog.getTypeId());
            TType type = typeService.getOne(typeQueryWrapper);
            blogVo.setTypeName(type.getName());
            blogVoList.add(blogVo);
        }
        Page<adminShowBlogVo> blogVoPage = new Page<>(Page.getCurrent(),Page.getSize(),Page.getTotal());
        blogVoPage.setRecords(blogVoList);
        return blogVoPage;
    }

    @Override
    public int getBlogViewsTotal() {
        QueryWrapper<TBlog> blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper.select("COALESCE(sum(views),0) as total");
        Map<String, Object> map = this.getMap(blogQueryWrapper);
        int total = Integer.parseInt(map.get("total").toString());
        return total;
    }

    @Override
    public int getblogCommentTotal() {
        return commentService.count();
    }

    @Override
    public int getBlogMessageTotal() {
        return messageService.count();
    }

}
