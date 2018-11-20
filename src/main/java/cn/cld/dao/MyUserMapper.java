package cn.cld.dao;

import cn.cld.pojo.UserInfo;
import cn.cld.pojo.lianxi.UserInfoListVo;

import java.util.List;

public interface MyUserMapper {
    List<UserInfo> queryUser(UserInfoListVo userInfoListVo);
}
