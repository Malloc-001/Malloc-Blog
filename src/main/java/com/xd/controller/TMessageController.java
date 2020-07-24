package com.xd.controller;


import cn.hutool.core.bean.BeanUtil;
import com.xd.entity.TMessage;
import com.xd.entity.TUser;
import com.xd.entityVO.MessageVo;
import com.xd.service.TMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
public class TMessageController {
    @Autowired
    TMessageService messageService;

    @GetMapping("/message")
    public String message() {
        return "message";
    }
    //    查询留言
    @GetMapping("/messagecomment")
    public String  messages(Model model) {
        List<MessageVo> allMessage = this.messageService.getAllMessage();
        model.addAttribute("messages", allMessage);
        return "message::messageList";
    }
//    新增留言
    @PostMapping("/message")
    public String saveMessage(MessageVo messageVo, HttpSession session,Model model){
        TUser user = (TUser) session.getAttribute("user");
        if (messageVo.getParentMessage().getId() != null) {
            messageVo.setParentMessageId(messageVo.getParentMessage().getId());
        }
        TMessage message = new TMessage();
        BeanUtil.copyProperties(messageVo,message);
        List<MessageVo> allMessage = messageService.saveMessage(message, user);
        model.addAttribute("messages", allMessage);
        return "message::messageList";
    }
}

