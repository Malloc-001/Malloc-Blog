package com.xd.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.entity.TBlog;
import com.xd.entity.TType;
import com.xd.entity.TUser;
import com.xd.entityVO.TBlogVo;
import com.xd.entityVO.TypeVo;
import com.xd.mapper.TTypeMapper;
import com.xd.service.TBlogService;
import com.xd.service.TTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class TTypeServiceImpl extends ServiceImpl<TTypeMapper, TType> implements TTypeService {

    @Autowired
    TBlogService blogService;
    @Autowired
    TUserService userService;

    @Override
    public Page<TBlogVo> getBlogByType(long id) {
        QueryWrapper<TBlog> blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper.eq("type_id",id)
        .orderByDesc("create_time");
        List<TBlog> blogList = this.blogService.list(blogQueryWrapper);
        List<TBlogVo> tBlogVoList = new ArrayList<>();
        for (TBlog blog : blogList){
            TBlogVo blogVo = new TBlogVo();
            BeanUtil.copyProperties(blog,blogVo);
//            设置blogVo的TypeName
            blogVo.setTypeName(this.getById(id).getName());
//            设置blogVo的nickName和头像
            QueryWrapper<TUser> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("id",blog.getUserId());
            TUser user = this.userService.getOne(userQueryWrapper);
            blogVo.setNickName(user.getNickname());
            blogVo.setAvatar(user.getAvatar());
            tBlogVoList.add(blogVo);
        }
        Page<TBlogVo> blogVoPage = new Page<>(1,1000,1000);
        blogVoPage.setRecords(tBlogVoList);
        return blogVoPage;
    }

    @Override
    public List<TypeVo> getAllTypeAndBlog() {
        List<TType> typeList = this.list();
        List<TypeVo> TWBLS = new ArrayList<>();
        for (TType type : typeList){
            TypeVo TWBL = new TypeVo();
            BeanUtil.copyProperties(type,TWBL);
            QueryWrapper<TBlog> blogQueryWrapper = new QueryWrapper<>();
            blogQueryWrapper.eq("type_id",type.getId());
            int count = this.blogService.count(blogQueryWrapper);
            TWBL.setBlogNum(count);
            TWBLS.add(TWBL);
        }
        return TWBLS;
    }

    @Override
    public Page<TType> getAllTypePage( Page<TType> page) {
        QueryWrapper<TType> typeQueryWrapper= new QueryWrapper<>();
        typeQueryWrapper.orderByDesc("id");
        Page<TType> typePage = this.page(page, typeQueryWrapper);
        return typePage;
    }

    @Override
    public TType getTypeByName(String name) {
        QueryWrapper<TType> typeQueryWrapper = new QueryWrapper<>();
        typeQueryWrapper.eq("name",name);
        return this.getOne(typeQueryWrapper);
    }
}
