package com.xd.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.entity.TPicture;
import com.xd.service.TPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * 图片管理
 * @author Malloc
 */
@Controller
@RequestMapping("/admin")
public class PictureController {
    @Autowired
    TPictureService pictureService;

    /**
     * 所有照片
     * @param model
     * @param current
     * @return
     */
    @GetMapping("/pictures")
    public String pictures(Model model, @RequestParam(defaultValue = "1",value = "current") Integer current) {
        Page<TPicture> picturePage = new Page<>(current,10);
        picturePage.setRecords(pictureService.getAllPicture());
        model.addAttribute("pageInfo",picturePage);
        return "admin/pictures";
    }

    /**
     *  跳转到新增页面
     * @param model
     * @return
     */
    @GetMapping("/pictures/input")
    public String input(Model model) {
        model.addAttribute("picture", new TPicture());
        return "admin/pictures-input";
    }

    /**
     * 新增照片
     * @param picture
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/pictures")
    public String post(TPicture picture, BindingResult result, RedirectAttributes attributes){
        boolean saveFlag = pictureService.save(picture);
        if (!saveFlag) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/pictures";
    }

    /**
     * 跳转到照片编译页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/pictures/input/{id}")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("picture", pictureService.getById(id));
        return "admin/pictures-input";
    }

    /**
     * 编辑照片
     * @param picture
     * @param attributes
     * @return
     */
    @PostMapping("/pictures/{id}")
    public String editPost( TPicture picture, RedirectAttributes attributes) {

        boolean updateFlag = pictureService.updateById(picture);
        if (!updateFlag ) {
            attributes.addFlashAttribute("message", "编辑失败");
        } else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/admin/pictures";
    }

    /**
     * 删除照片
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/pictures/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        boolean removeFlag = pictureService.removeById(id);
        if (!removeFlag) {
            attributes.addFlashAttribute("message", "删除失败");
        } else {
            attributes.addFlashAttribute("message", "删除成功");
        }
        return "redirect:/admin/pictures";
    }
}
