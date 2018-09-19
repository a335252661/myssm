package cn.cld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("register")
    public String regist(HttpServletRequest request, Model model){
        return "register";
    }

    //@ResponseBody
    @RequestMapping("query")
    public String userList(HttpServletRequest request, Model model){
        return "userList";
    }
}
