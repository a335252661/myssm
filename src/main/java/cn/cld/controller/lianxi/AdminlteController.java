package cn.cld.controller.lianxi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("adminlte")
public class AdminlteController {

    @RequestMapping("")
    public String adminlte(ModelAndView mav, HttpServletRequest request){

        return "adminlte/pages/index_iframe";
    }
}
