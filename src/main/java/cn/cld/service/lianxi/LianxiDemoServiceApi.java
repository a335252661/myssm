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

    /**
     * 用户导入
     * @param myData
     * @param logsId
     * @return
     */
    MessageResult userListUplode(List<Map<String,String>> myData , int logsId);

    String[][] userListExport(UserInfoListVo userInfoListVo);

    //打印预览
    MessageResult userListPrint(int userId);

    /**
     * txt下载
     * @param userInfoListVo
     * @param path2
     * @return
     */
    MessageResult downLoadTxt(UserInfoListVo userInfoListVo, String path2);

    /**
     * txt下载不生成临时文件
     * @param userInfoListVo
     * @return
     */
    MessageResult downLoadTxt2(UserInfoListVo userInfoListVo);

    String[][] add(UserInfoListVo userInfoListVo);

    /**
     *  获取 数据
     * @param userInfoListVo
     */
    List<List<Map<String, Object>>> getExcelData(UserInfoListVo userInfoListVo);
}
