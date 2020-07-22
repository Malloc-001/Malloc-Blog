package com.xd.controller;


import com.xd.entity.TPicture;
import com.xd.service.TPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
public class TPictureController {

    @Autowired
    TPictureService pictureService;

    @GetMapping("/picture")
    public String getAllPicture(Model model){
        List<TPicture> allPicture = pictureService.getAllPicture();
        model.addAttribute("pictures",allPicture);
        return "picture";
    }
}

