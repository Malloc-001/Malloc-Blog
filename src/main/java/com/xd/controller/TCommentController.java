package com.xd.controller;


import cn.hutool.core.bean.BeanUtil;
import com.xd.entity.TComment;
import com.xd.entity.TUser;
import com.xd.entityVO.CommentVo;
import com.xd.entityVO.DetailBlogVo;
import com.xd.service.TBlogService;
import com.xd.service.TCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Malloc
 * @since 2020-07-20
 */
@Controller
public class TCommentController {
    @Autowired
    TCommentService commentService;
    @Autowired
    TBlogService blogService;


    /**
     * 通过id查询博客文章的评论
     * @param blogId
     * @param model
     * @return
     */
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        List<CommentVo> allComment = this.commentService.getCommentByBlogId(blogId);
        model.addAttribute("comments", allComment);
        return "blog :: commentList";
    }

    /**
     * 新增评论
     * @param commentVo
     * @param session
     * @param model
     * @return
     */
    @PostMapping("/comments")
    public String saveComment(CommentVo commentVo, HttpSession session, Model model) {
        Long blogId = commentVo.getBlogId();
        TUser user = (TUser) session.getAttribute("user");
        commentService.saveComment(commentVo,user);
        model.addAttribute("comments", this.commentService.getCommentByBlogId(blogId));
        return "blog :: commentList";
    }
    /**
     * 删除评论
     */
    @GetMapping("/comment/delete/{blogId}/{id}")
    public String deleteComment(@PathVariable Long blogId, @PathVariable Long id,TComment comment, Model model){
        commentService.removeById(id);
        DetailBlogVo detailedBlog = blogService.getDetailedBlog(blogId);
        List<CommentVo> comments = commentService.getCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        model.addAttribute("blog", detailedBlog);
        return "blog";
    }
}

