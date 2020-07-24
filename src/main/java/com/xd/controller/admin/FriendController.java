package com.xd.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.entity.TFriend;
import com.xd.service.TFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

/**
 * 后台友情链接管理
 * @author Malloc
 */
@Controller
@RequestMapping("/admin")
public class FriendController {

    @Autowired
    TFriendService friendService;

    /**
     * 查询所有友链
     * @param model
     * @param current
     * @return
     */
    @GetMapping("/friendlinks")
    public String friend(Model model, @RequestParam(value = "current" , defaultValue = "1") Integer current){
        List<TFriend> friendList = friendService.getAllFriend();
        Page<TFriend> friendPage = new Page<>(current,10,friendList.size());
        friendPage.setRecords(friendList);
        model.addAttribute("pageInfo",friendPage);
        return "admin/friendlinks";
    }

    /**
     * 跳转到友链新增页面
     * @param model
     * @return
     */
    @GetMapping("/friendlinks/input")
    public String input(Model model) {
        model.addAttribute("friendlink", new TFriend());
        return "admin/friendlinks-input";
    }

    /**
     * 新增友链
     * @param friendLink
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/friendlinks")
    public String post( TFriend friendLink, BindingResult result, RedirectAttributes attributes){
        boolean sameLinkFlag = friendService.isSameLink(friendLink.getBlogaddress());
        if (sameLinkFlag){
            attributes.addFlashAttribute("message", "不能添加相同的网址");
            return "redirect:/admin/friendlinks/input";
        }
        if(result.hasErrors()){
            return "admin/friendlinks-input";
        }
        boolean saveFlah = friendService.save(friendLink);
        if (saveFlah){
            attributes.addFlashAttribute("message", "新增成功");
        }else {
            attributes.addFlashAttribute("message", "新增失败");
        }
        return "redirect:/admin/friendlinks";
    }

    /**
     * 删除友链
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/friendlinks/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        boolean removeFlag = friendService.removeById(id);
        if (removeFlag){
            attributes.addFlashAttribute("message", "删除成功");
        }else {
            attributes.addFlashAttribute("message", "删除失败");
        }

        return "redirect:/admin/friendlinks";
    }

    /**
     * 跳转到编辑友链页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/friendlinks/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("friendlink", friendService.getById(id));
        return "admin/friendlinks-input";
    }

    /**
     * 编辑友链页面
     * @param friendLink
     * @param attributes
     * @return
     */
    @PostMapping("/friendlinks/{id}")
    public String editPost(TFriend friendLink, RedirectAttributes attributes) {
        friendService.updateById(friendLink);
        return "redirect:/admin/friendlinks";
    }
}
