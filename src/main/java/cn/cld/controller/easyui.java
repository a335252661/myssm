package cn.cld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 程刘德
 * @function
 * @date 2018/9/19 13:35
 */
@Controller
@RequestMapping("easyui")
public class easyui {
    @RequestMapping("demo1")
    public String index(HttpServletRequest request, Model model){
        return "ResourceBundleDemo1";
    }

    //panel
    @RequestMapping("panel")
    public String panel(HttpServletRequest request, Model model){
        return "panel";
    }

}
