package com.xd.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.entityVO.TBlogVo;
import com.xd.entityVO.TypeVo;
import com.xd.service.TTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
@Controller
@RequestMapping("/types")
public class TTypeController {

    @Autowired
    TTypeService typeService;
    @GetMapping("/{id}")
    public String types(@PathVariable Long id, Model model) throws Exception {
        List<TypeVo> allTypes = typeService.getAllTypeAndBlog();
        if (id == -1){
            if (!allTypes.isEmpty()){
                id = allTypes.get(0).getId();
            }

        }
        Page<TBlogVo> blogVoPage = typeService.getBlogByType(id);
        model.addAttribute("types", allTypes);
        model.addAttribute("pageInfo", blogVoPage);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}

