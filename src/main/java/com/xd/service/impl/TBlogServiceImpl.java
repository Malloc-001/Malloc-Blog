package com.xd.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.entity.TBlog;
import com.xd.entity.TType;
import com.xd.entity.TUser;
import com.xd.entityVO.TBlogVo;
import com.xd.mapper.TBlogMapper;
import com.xd.service.TBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.service.TTypeService;
import com.xd.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        blogVoPage.setPages(blogPage.getPages());
        return blogVoPage;
    }
}
