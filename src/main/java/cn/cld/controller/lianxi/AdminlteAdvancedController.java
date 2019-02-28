package cn.cld.controller.lianxi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("advanced")
public class AdminlteAdvancedController {

    @RequestMapping("")
    public String advanced(ModelAndView mav, HttpServletRequest request){

        return "adminlte/pages/forms/advanced_iframe";
    }
}
