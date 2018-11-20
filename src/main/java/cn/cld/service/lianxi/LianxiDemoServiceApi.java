package cn.cld.service.lianxi;

import cn.cld.pojo.UserInfo;
import cn.cld.pojo.basic.MessageResult;
import cn.cld.pojo.basic.PageQueryResult;
import cn.cld.pojo.basic.SimpleServiceResult;
import cn.cld.pojo.lianxi.UserInfoListVo;

import java.util.List;
import java.util.Map;

public interface LianxiDemoServiceApi {
    //查询用户
    PageQueryResult<UserInfo> queryUserInfo(UserInfoListVo userInfoListVo);

    MessageResult addUser(UserInfo vo);

    MessageResult deleteUserInfo(List<UserInfo> userInfo);

    MessageResult userListUplode(List<Map<String,String>> myData);

    String[][] userListExport(UserInfoListVo userInfoListVo);

    //打印预览
    MessageResult userListPrint(int userId);
}
