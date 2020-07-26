package com.xd.controller.admin;

import com.xd.entity.TUser;
import com.xd.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    TUserService userService;

    @GetMapping
    public String loginPage(HttpSession session){
        if (session.getAttribute("user") != null){
            return "redirect:admin/blogs";
        }
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        TUser user = userService.checkUser(username,password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "admin/login";
        }
    }

    /**
     * @Description:注销
     * @param session
     * @return 登录页面
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
