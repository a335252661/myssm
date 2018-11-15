package cn.cld.controller.lianxi;

import cn.cld.pojo.UserInfo;
import cn.cld.pojo.basic.MessageResult;
import cn.cld.pojo.basic.PageQueryResult;
import cn.cld.pojo.lianxi.UserInfoListVo;
import cn.cld.service.lianxi.LianxiDemoServiceApi;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LianxiDemoController {

    @Resource
    private LianxiDemoServiceApi lianxiDemoServiceApi;

//    @RequestMapping("")
//    public ModelAndView index(ModelAndView mav,HttpServletRequest request){
//        mav.setViewName("panel");
//        return  mav;
//    }

    @RequestMapping("lianxiEasyui")
    public String easyui(ModelAndView mav,HttpServletRequest request){
        return  "lianxiEasyui";
    }

    @RequestMapping("query")
    @ResponseBody
    public PageQueryResult<UserInfo> query(UserInfoListVo userInfo){
        System.out.println("开始查询");
        PageQueryResult<UserInfo> resultUserInfo = lianxiDemoServiceApi.queryUserInfo(userInfo);
        return resultUserInfo;
    }
    //delete
    @RequestMapping("delete")
    @ResponseBody
    public MessageResult delete(@RequestBody List<UserInfo> userInfoList){

        MessageResult result = lianxiDemoServiceApi.deleteUserInfo(userInfoList);
        return result;
    }

}
