package com.xd.controller;


import com.xd.entityVO.CommentVo;
import com.xd.service.TCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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
    //    查询评论列表
    @ResponseBody
    @GetMapping("/comments/{blogId}")
    public List<CommentVo> comments(@PathVariable Long blogId, Model model) {
        List<CommentVo> allComment = this.commentService.getCommentByBlogId(blogId);
        model.addAttribute("comments", allComment);
        return allComment;
    }
}

