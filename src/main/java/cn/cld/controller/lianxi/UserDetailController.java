package cn.cld.controller.lianxi;

import cn.cld.controller.BaseController.BaseController;
import cn.cld.pojo.UserInfo;
import cn.cld.pojo.basic.JsonResult;
import cn.cld.pojo.basic.MessageResult;
import cn.cld.pojo.basic.SimpleServiceResult;
import cn.cld.pojo.lianxi.UserInfoListVo;
import cn.cld.service.lianxi.LianxiDemoServiceApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="userDetail")
public class UserDetailController extends BaseController {
    @Resource
    private LianxiDemoServiceApi lianxiDemoServiceApi;

    @RequestMapping(value="")
    public ModelAndView index(ModelAndView mav, HttpServletRequest request){
        mav.setViewName("userDetail");
        return mav;
    }

    @RequestMapping(value="addUser")
    @ResponseBody
    public MessageResult addUser(UserInfo vo, HttpServletRequest request){
        MessageResult result =lianxiDemoServiceApi.addUser(vo);
        return result;
    }
}
