package cn.cld.controller.layUi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("layUiLogQuery")
public class LayUiLogQueryController {

    @RequestMapping("")
    private String layUiLogQuery(){
        return "layui/layUiLogQuery";
    }
}
