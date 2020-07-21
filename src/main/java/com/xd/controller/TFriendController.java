package com.xd.controller;


import com.xd.entity.TFriend;
import com.xd.service.TFriendService;
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
public class TFriendController {
    @Autowired
    TFriendService friendService;
    @GetMapping("/friends")
    public String friends(Model model) {
        List<TFriend> friendList = friendService.getAllFriend();
        model.addAttribute("friendlinks",friendList);
        return "friends";
    }
}

