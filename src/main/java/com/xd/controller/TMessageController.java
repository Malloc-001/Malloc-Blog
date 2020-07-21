package com.xd.controller;


import com.xd.entity.TMessage;
import com.xd.service.TMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

import static com.sun.org.apache.xml.internal.serializer.utils.Utils.messages;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Malloc
 * @since 2020-07-20
 */
@Controller
public class TMessageController {
    @Autowired
    TMessageService messageService;
    @GetMapping("/message")
    public String message() {
        return "message";
    }
    //    查询留言
    @GetMapping("/messagecomment")
    public String messages(Model model) {
        List<TMessage> allMessage = this.messageService.getAllMessage();
        model.addAttribute("messages", allMessage);
        return "message::messageList";
    }
}

