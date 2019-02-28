package cn.cld.controller.lianxi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("bootstrap")
public class BootstrapController {


    @RequestMapping("")
    public String bootstrap(ModelAndView mav, HttpServletRequest request){

        return "bootstrap/bootstrap";
    }
}
