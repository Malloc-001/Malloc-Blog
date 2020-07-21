package com.xd.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.entity.TBlog;
import com.xd.entity.TComment;
import com.xd.entityVO.DetailBlogVo;
import com.xd.entityVO.RecommendBlogVo;
import com.xd.entityVO.TBlogVo;
import com.xd.service.TBlogService;
import com.xd.service.TCommentService;
import com.xd.service.TMessageService;
import com.xd.service.impl.TBlogServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Malloc
 * @since 2020-07-20
 */
@Api(value = "blog", tags = "博客首页管理")
@Controller
public class TBlogController {
    @Autowired
    TBlogService blogService;

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/")
    public String index(Page<TBlog> blogPage, Model model){
        Page<TBlogVo> allBlog = blogService.getAllBlog(blogPage);
        model.addAttribute("pageInfo",allBlog);
        List<RecommendBlogVo> recommendedBlog = blogService.getRecommendBlog();
        model.addAttribute("recommendedBlogs", recommendedBlog);
        return "index";
    }
    //    搜索博客
    @PostMapping("/search")
    public String search(Model model,
                         @RequestParam String query) {
        Page<TBlogVo> searchBlog = blogService.getSearchBlog(query);
//        PageHelper.startPage(pageNum, 1000);
//        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);
//        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", searchBlog);
        model.addAttribute("query", query);
        return "search";
    }
    //    博客详情页面
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
//        DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
        DetailBlogVo detailedBlog = blogService.getDetailedBlog(id);
//        List<Comment> comments = commentService.listCommentByBlogId(id);
        List<TComment> comments = blogService.getCommentsByBlogId(id);
        model.addAttribute("comments", comments);
        model.addAttribute("blog", detailedBlog);
        return "blog";
    }
    //    博客信息
    @GetMapping("/footer/blogmessage")
    public String blogMessage(Model model){
        int blogTotal = blogService.count();
        int blogViewsTotal = blogService.getBlogViewsTotal();
        int blogCommentTotal = blogService.getblogCommentTotal();
        int blogMessageTotal = blogService.getBlogMessageTotal();
        model.addAttribute("blogTotal",blogTotal);
        model.addAttribute("blogViewTotal",blogViewsTotal);
        model.addAttribute("blogCommentTotal",blogCommentTotal);
        model.addAttribute("blogMessageTotal",blogMessageTotal);
        return "index :: blogMessage";
    }


}

