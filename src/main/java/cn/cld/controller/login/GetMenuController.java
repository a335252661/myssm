package cn.cld.controller.login;

import cn.cld.controller.lianxi.LianxiDemoController;
import cn.cld.service.login.HomeServiceApi;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GetMenuController {

    protected static final Logger logger = LoggerFactory.getLogger(GetMenuController.class);
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
        List ob = homeServiceApi.getJsonMenu2();
        logger.info("获取菜单开始2");
        JSONArray js = new JSONArray(ob);
        System.out.println(js);
        return ob;
    }
}
