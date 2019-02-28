package cn.cld.controller.lianxi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("layui")
public class LayuiController {
    @RequestMapping("")
    public String layui(ModelAndView mav, HttpServletRequest request){

        return "layui/layui";
    }
}
