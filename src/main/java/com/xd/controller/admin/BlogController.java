package com.xd.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.entity.TBlog;
import com.xd.entity.TType;
import com.xd.entity.TUser;
import com.xd.entityVO.RecommendBlogVo;
import com.xd.entityVO.adminShowBlogVo;
import com.xd.mapper.TBlogMapper;
import com.xd.service.TBlogService;
import com.xd.service.TTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Malloc
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    TBlogService blogService;
    @Autowired
    TTypeService typeService;
    @Autowired
    TBlogMapper blogMapper;

    /**
     * 后台博客文章展示页面
     * @param model
     * @param page
     * @return
     */
    @RequestMapping("/blogs")
    public String blogs(Model model, Page<TBlog> page){
        Page<adminShowBlogVo> adminShowBlogPage = blogService.getAdminShowBlog(page);
        List<TType> types = typeService.list();
        model.addAttribute("types",types);
        model.addAttribute("pageInfo",adminShowBlogPage);
        return "admin/blogs";
    }

    /**
     * 跳转到博客文章编辑页面
     * @param model
     * @return
     */
    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types",typeService.list());
        model.addAttribute("blog", new TBlog());
        return "admin/blogs-input";
    }

    /**
     * 新增博客文章
     * @param blog
     * @param attributes
     * @param session
     * @return
     */
    @PostMapping("/blogs")
    public String post(TBlog blog, RedirectAttributes attributes, HttpSession session){
//        设置博客文章作者id
        TUser user = (TUser) session.getAttribute("user");
        blog.setUserId(user.getId());
        int saveFlag = blogMapper.insert(blog);
        if(saveFlag == 1){
            attributes.addFlashAttribute("message", "新增成功");
        }else {
            attributes.addFlashAttribute("message", "新增失败");
        }
        return "redirect:/admin/blogs";
    }
    /**
     * 删除博客文章
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/blogs/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        boolean remove = blogService.removeById(id);
        if (remove){
            attributes.addFlashAttribute("message", "删除成功");
        }else {
            attributes.addFlashAttribute("message", "删除失败");
        }
        return "redirect:/admin/blogs";
    }

    /**
     * 跳转到修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blogs/toInput/{id}")
    public String editInput(@PathVariable Long id, Model model) {
        TBlog blog = blogService.getById(id);
        List<TType> allType = typeService.list();
        model.addAttribute("blog", blog);
        model.addAttribute("types", allType);
        return "admin/blogs-input";
    }

    /**
     * 修改文章
     * @param blog
     * @return
     */
    @PostMapping("/blogs/{id}")
    public String editPost(TBlog blog, HttpSession session) {
        if (blog.getAppreciation() == null){
            blog.setAppreciation(false);
        }if (blog.getCommentabled() == null){
            blog.setCommentabled(false);
        }if (blog.getRecommend() == null){
            blog.setRecommend(false);
        }if (blog.getShareStatement() == null){
            blog.setShareStatement(false);
        }
        TUser user = (TUser) session.getAttribute("user");
        blog.setUserId(user.getId());
        blogService.updateById(blog);
        return "redirect:/admin/blogs";
    }

    /**
     * 后台查询博客文章
     * @param searchBlog
     * @param model
     * @return
     */
    @PostMapping("/blogs/search")
    public String search(RecommendBlogVo searchBlog, Model model) {
//        System.out.println(searchBlog);
        Page<adminShowBlogVo> blogVoPage = blogService.adminSearchBlog(searchBlog);
        model.addAttribute("pageInfo", blogVoPage);
        return "admin/blogs :: blogList";
    }

}
