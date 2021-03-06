package cn.cld.serviceImpl.layui;

import cn.cld.dao.UserInfoMapper;
import cn.cld.pojo.UserInfo;
import cn.cld.pojo.UserInfoExample;
import cn.cld.pojo.basic.LayuiPageQueryResult;
import cn.cld.pojo.basic.MessageResult;
import cn.cld.pojo.lianxi.UserInfoListVo;
import cn.cld.service.layui.LayuiBaseQueryApi;
import cn.cld.untils.DateTimeUtils;
import cn.cld.untils.StringUtils;
import org.apache.ibatis.session.RowBounds;

import javax.annotation.Resource;
import java.util.List;

public class LayuiBaseQueryApiImpl implements LayuiBaseQueryApi {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public LayuiPageQueryResult<UserInfo> layuiQueryUserInfo(UserInfoListVo params) {

        LayuiPageQueryResult<UserInfo> pageQueryResult = new LayuiPageQueryResult<UserInfo>();


        //总数
        UserInfoExample ue = new UserInfoExample();
        UserInfoExample.Criteria criteria = ue.createCriteria();
        if(null!=params.getUserId()){
            criteria.andUserIdEqualTo(params.getUserId());
        }
        if(StringUtils.hasText(params.getUserName())){
            criteria.andUserNameLike("%"+params.getUserName()+"%");
        }
        //大于等于
        if(StringUtils.hasText(params.getRegisterStart())){
            criteria.andCreatDateGreaterThanOrEqualTo(DateTimeUtils.parseStr(params.getRegisterStart(),DateTimeUtils.YYYY_MM_DD));
        }
        if(StringUtils.hasText(params.getRegisterEnd())){
            criteria.andCreatDateLessThanOrEqualTo(DateTimeUtils.parseStr(params.getRegisterEnd(),DateTimeUtils.YYYY_MM_DD));
        }
        if(null!=params.getIsUse()){
            criteria.andIsUseEqualTo(params.getIsUse());
        }


        int count = userInfoMapper.countByExample(ue);
        pageQueryResult.setCount(count);
        // 分页数据
        RowBounds rowBounds = new RowBounds((params.getPage() - 1) * params.getLimit(), params.getLimit());
        List<UserInfo> userInfos = userInfoMapper.selectByExampleWithRowbounds(ue, rowBounds);

        pageQueryResult.setData(userInfos);

        return pageQueryResult;
    }

    /**
     * 行编辑修改更新
     * @param userInfo
     * @return
     */
    @Override
    public MessageResult layuiuUpdateUserInfo(UserInfoListVo userInfo) {
        MessageResult result = new MessageResult();
        //用户名重复提示
        UserInfo ui = new UserInfo();
        ui.setUserName(userInfo.getUserName());

        UserInfoExample uie = new UserInfoExample();
        uie.createCriteria().andUserNameEqualTo(userInfo.getUserName());

        List<UserInfo> userInfoList = userInfoMapper.selectByExample(uie);
        if(userInfoList.isEmpty()){
            //修改的用户名不存在同名
            //进行更新

            UserInfoExample uieex = new UserInfoExample();
            uieex.createCriteria().andUserIdEqualTo(userInfo.getUserId());

            int i = userInfoMapper.updateByExampleSelective(ui, uieex);
           // if (1==i){
                result.setMessage("用户名修改完成！");
           // }

            return result;
        }else {
            result.setMessage("用户名重复！");
            result.setResult(false);
            return result;
        }
    }

    @Override
    public MessageResult ftpDowmLoad() {
        MessageResult result = new MessageResult();

//        FtpUtil.downloadFtpFile("192.168.110.124",null,null,
//                21,"downLoad","C:\\tmp","11.txt");



//        boolean downLoad = FtpUtil.downFile("192.168.110.124", 21,
//                null, null,
//                "downLoad", "11.txt",
//                "C:\\tmp");
//        if(downLoad){
//            result.setMessage("下载成功！");
//        }else {
//            result.setResult(false);
//            result.setMessage("下载失败！");
//        }
        return result;
    }
}
