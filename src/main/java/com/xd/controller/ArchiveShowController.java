package com.xd.controller;

import com.xd.entityVO.TimeAxisVo;
import com.xd.service.TimeAxisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Malloc
 * @since 2020-07-21
 */
@Controller
public class ArchiveShowController {

    @Autowired
    TimeAxisService timeAxisService;

    @GetMapping("/archives")
    public String archive(Model model){
//        List<BlogQuery> blogs = blogService.getAllBlog();
        List<TimeAxisVo> allTimeAxisVo = timeAxisService.getAllTimeAxisVo();
        model.addAttribute("blogs", allTimeAxisVo);
        return "archives";
    }

}