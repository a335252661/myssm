package cn.cld.controller.layUi;

import cn.cld.pojo.UserInfo;
import cn.cld.pojo.basic.JsonResult;
import cn.cld.pojo.basic.LayuiPageQueryResult;
import cn.cld.pojo.basic.MessageResult;
import cn.cld.pojo.lianxi.UserInfoListVo;
import cn.cld.service.layui.LayuiBaseQueryApi;
import cn.cld.service.lianxi.LianxiDemoServiceApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("layUiBaseQuery")
public class LayUiBaseQueryController {

    @Resource
    private LayuiBaseQueryApi layuiBaseQueryApi;

    @RequestMapping("")
    private String layUiBaseQuery(){
        return "layui/layUiBaseQuery";
    }

    @RequestMapping("layuiQuery")
    @ResponseBody
    public LayuiPageQueryResult<UserInfo> query(UserInfoListVo userInfo){
        System.out.println("开始查询");
        LayuiPageQueryResult<UserInfo> resultUserInfo = layuiBaseQueryApi.layuiQueryUserInfo(userInfo);
        return resultUserInfo;
    }


    /**
     * 行编辑修改更新
     * @param userInfo
     * @return
     */
    @RequestMapping("layuiUpdate")
    @ResponseBody
    public MessageResult layuiUpdate(UserInfoListVo userInfo){
        MessageResult result = layuiBaseQueryApi.layuiuUpdateUserInfo(userInfo);
        return result;
    }


    /**
     * 从ftp服务器下载文件
     * @return
     */
    @RequestMapping("downLoad")
    @ResponseBody
    public MessageResult downLoad(){
        MessageResult result = layuiBaseQueryApi.ftpDowmLoad();
        return result;
    }

}
