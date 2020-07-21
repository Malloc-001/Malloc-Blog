package com.xd.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.entity.TBlog;
import com.xd.entityVO.TBlogVo;
import com.xd.service.TBlogService;
import com.xd.service.impl.TBlogServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

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
    TBlogServiceImpl blogServiceImpl;

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/")
    public String index(Page<TBlog> blogPage, Model model){

//        Page<TBlog> page =new Page<>(pageNum,10);
//        BlogMapper.selectPage(page,null);

        Page<TBlogVo> allBlog = blogServiceImpl.getAllBlog(blogPage);
        System.out.println(allBlog.getRecords());
//        System.out.println(pageNum);
        model.addAttribute("pageInfo",allBlog);
//        List<TBlog> allBlog = blogServiceImpl.getAllBlog();
//        Page<TBlog> tBlogPage = new Page<>();

        return "index";
    }

}

