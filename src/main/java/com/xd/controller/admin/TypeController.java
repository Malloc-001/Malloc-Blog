package com.xd.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.entity.TType;
import com.xd.service.TTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    TTypeService typeService;
    /**
     *  分页查询类型
     */
    @GetMapping("/types")
    public String AdminTypePage(Model model, Page<TType> page) {
        Page<TType> allTypePage = typeService.getAllTypePage(page);
        model.addAttribute("pageInfo",allTypePage);
        return "admin/types";
    }

    /**
     *  前往新增页面
     * @param model
     * @return
     */
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type", new TType());
        return "admin/types-input";
    }

    /**
     * 新增分类
     * @param type
     * @param attributes
     * @return
     */
    @PostMapping("/types")
    public String post(TType type, RedirectAttributes attributes) {
        TType type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        boolean save = typeService.save(type);
        if (save) {
            attributes.addFlashAttribute("message", "新增成功");
        } else {
            attributes.addFlashAttribute("message", "新增失败");
        }
        return "redirect:/admin/types";
    }

    /**
     * 更新分类
     * @param Type 页面传来的分类对象
     * @param attributes
     * @return
     */
    @PostMapping("/types/update/{id}")
    public String updateType(TType Type,RedirectAttributes attributes){
        boolean updateFlag = typeService.updateById(Type);
        if (updateFlag){
            attributes.addFlashAttribute("message","更新成功");
        }else {
            attributes.addFlashAttribute("message","更新失败");
        }
        return "redirect:/admin/types";
    }

    /**
     * 带着type的id跳转到修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/types/toUpdate/{id}")
    public String toUpdateType(@PathVariable("id") Long id, Model model){
        TType type = typeService.getById(id);
        model.addAttribute("type",type);
        return "admin/types-input";
    }
    /**
     * 删除一个分类
     * @param id 分类id
     * @param attributes 传参
     * @return
     */
    @GetMapping("/types/delete/{id}")
    public String deleteType(@PathVariable("id") Long id, RedirectAttributes attributes){
        boolean removeFlag = typeService.removeById(id);
        if (removeFlag){
            attributes.addFlashAttribute("message", "删除成功");
        }else {
            attributes.addFlashAttribute("message", "删除失败");
        }
        return "redirect:/admin/types";
    }
}
