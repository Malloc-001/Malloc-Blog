package com.xd.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.entity.TBlog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.entityVO.RecommendBlogVo;
import com.xd.entityVO.TBlogVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Malloc
 * @since 2020-07-20
 */
public interface TBlogService extends IService<TBlog> {

    Page<TBlogVo> getAllBlog(Page<TBlog> Page);

    Page<TBlogVo> getSearchBlog(String queryContent);

    List<RecommendBlogVo> getRecommendBlog();

    int getBlogViewsTotal();

    int getblogCommentTotal();

    int getBlogMessageTotal();
}
