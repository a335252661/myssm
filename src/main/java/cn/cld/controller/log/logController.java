package cn.cld.controller.log;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("queryLogList")
public class logController {

    @RequestMapping("")
    public String queryLog(ModelAndView mav, HttpServletRequest request){
        return  "queryLogList";
    }
}
