package cn.cld.controller.lianxi;

import cn.cld.pojo.UserInfo;
import cn.cld.pojo.basic.MessageResult;
import cn.cld.pojo.basic.PageQueryResult;
import cn.cld.pojo.lianxi.UserInfoListVo;
import cn.cld.service.lianxi.LianxiDemoServiceApi;
import cn.cld.untils.XlsxReaderUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Controller
public class LianxiDemoController {

    protected static final Logger logger = LoggerFactory.getLogger(LianxiDemoController.class);

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

    //userListUplode
    @RequestMapping(value = "userListUplode",method=RequestMethod.POST/*,headers="Content-Type=multipart/form-data"*/)
    @ResponseBody
    public MessageResult userListUplode(@RequestParam("file") MultipartFile file,HttpServletRequest request){

        MessageResult result = new MessageResult();
        try{
            //文件内容
            List<Map<String,String>> myData = XlsxReaderUtil.getSheetData(file.getInputStream(),0);
             result = lianxiDemoServiceApi.userListUplode(myData);
        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return result;
    }

}
