package cn.cld.controller;


import org.apache.velocity.tools.view.servlet.VelocityViewServlet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController extends VelocityViewServlet {
    @RequestMapping("login")
    public ModelAndView userlogin(ModelAndView mav,HttpServletRequest request, Model model){
        System.out.println("登录系统++++++++++++");
        mav.setViewName("home");
        return mav;
    }
}
