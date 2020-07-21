package com.xd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Malloc
 * @since 2020-07-21
 */
@Controller
public class MusicShowController {

    @GetMapping("/music")
    public String about() {
        return "music";
    }

}