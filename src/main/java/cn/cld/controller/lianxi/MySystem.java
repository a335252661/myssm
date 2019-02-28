package cn.cld.controller.lianxi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("mySystem")
public class MySystem {

    @RequestMapping("")
    public String mySystem(ModelAndView mav, HttpServletRequest request){

        return "bootstrap/mySystem";
    }
}
