package cn.cld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("tree")
public class TreeController {
    @RequestMapping("init")
    public String init(HttpServletRequest request, Model model){
        return "register";
    }


}
