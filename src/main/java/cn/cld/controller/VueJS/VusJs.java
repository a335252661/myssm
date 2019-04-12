package cn.cld.controller.VueJS;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("vuejs")
public class VusJs {


        @RequestMapping("")
    private String vuejs(){
        return "vuejs/test";
    }
}
//@Controller
//@RequestMapping("layUiLogQuery")
//public class LayUiLogQueryController {
//
//    @RequestMapping("")
//    private String layUiLogQuery(){
//        return "layui/layUiLogQuery";
//    }
//}
