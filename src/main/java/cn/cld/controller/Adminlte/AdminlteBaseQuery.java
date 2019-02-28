package cn.cld.controller.Adminlte;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("baseQuery")
public class AdminlteBaseQuery {

    @RequestMapping("")
    private String baseQuery(){
        return "adminlte/menu";
    }
}
