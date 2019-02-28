package cn.cld.service.layui;

import cn.cld.pojo.UserInfo;
import cn.cld.pojo.basic.LayuiPageQueryResult;
import cn.cld.pojo.basic.MessageResult;
import cn.cld.pojo.lianxi.UserInfoListVo;

public interface LayuiBaseQueryApi {
    LayuiPageQueryResult<UserInfo> layuiQueryUserInfo(UserInfoListVo userInfo);

    MessageResult layuiuUpdateUserInfo(UserInfoListVo userInfo);

    MessageResult ftpDowmLoad();
}
