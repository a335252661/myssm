package cn.cld.controller.login;

import cn.cld.service.login.HomeServiceApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GetMenuController {

    @Resource
    private HomeServiceApi homeServiceApi;

    /**
     * 首页
     * @param mav
     * @return
     */
    @RequestMapping(value="")
    public ModelAndView index(ModelAndView mav,HttpServletRequest request){
        mav.setViewName("home");
        return mav;
    }


    /**
     * 取得菜单
     *
     * @param request
     * @param mav
     * @return
     */
    @RequestMapping("menu")
    @ResponseBody
    public List menu2(HttpServletRequest request, ModelAndView mav){
        List ob = homeServiceApi.getJsonMenu();
        System.out.println(ob);
        return ob;
    }
}
