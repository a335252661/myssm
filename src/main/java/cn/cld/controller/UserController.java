package cn.cld.controller;

import cn.cld.service.userServiceApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user")
public class UserController {

/*    @Resource
    private userServiceApi userService;*/
/*    @RequestMapping("")
    public ModelAndView index(HttpServletRequest request){
        ModelAndView mav =new ModelAndView();
        mav.setViewName("userList");
        return mav;
    }*/

    @RequestMapping("register")
    public String regist(HttpServletRequest request, Model model){
        return "register";
    }

    //@ResponseBody
    @RequestMapping("query")
    public String userList(HttpServletRequest request, Model model){
       // userService.query();
        System.out.println(000000000000);

        return "userList";
    }
}
