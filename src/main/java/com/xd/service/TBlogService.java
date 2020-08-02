package com.xd.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.entity.TBlog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.entity.TComment;
import com.xd.entityVO.DetailBlogVo;
import com.xd.entityVO.adminShowBlogVo;
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

    /**
     * 访客博客文章展示页
     * @param Page
     * @return
     */
    Page<TBlogVo> getAllBlog(Page<TBlog> Page);

    /**
     * 查询博客
     * @param queryContent 查询内容
     * @return
     */
    Page<TBlogVo> getSearchBlog(String queryContent);
    /**
     *
     */
    Page<adminShowBlogVo> adminSearchBlog(RecommendBlogVo searchBlog);
    /**
     * 获取被推荐的博客文章
     * @return
     */
    List<RecommendBlogVo> getRecommendBlog();

    /**
     * 根据id获得博客文章的详情
     * @param id
     * @return
     */
    DetailBlogVo getDetailedBlog(Long id);

    /**
     * 获取后台展示的博客
     * @param page
     * @return
     */
    Page<adminShowBlogVo> getAdminShowBlog(Page<TBlog> page);

    /**
     * 获取博客文章的总浏览量
     * @return
     */
    int getBlogViewsTotal();
    /**
     * 获取博客文章的总评论数
     * @return
     */
    int getblogCommentTotal();
    /**
     * 获取博客文章的总留言数
     * @return
     */
    int getBlogMessageTotal();

    /**
     * 更新评论数和浏览量
     * @param id
     */
    void updateCommentAndViews(Long id);
}
