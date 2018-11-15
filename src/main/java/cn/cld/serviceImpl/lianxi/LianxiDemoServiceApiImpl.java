package cn.cld.serviceImpl.lianxi;

import cn.cld.dao.UserInfoMapper;
import cn.cld.pojo.UserInfo;
import cn.cld.pojo.UserInfoExample;
import cn.cld.pojo.basic.MessageResult;
import cn.cld.pojo.basic.PageQueryResult;
import cn.cld.pojo.basic.SimpleServiceResult;
import cn.cld.pojo.lianxi.UserInfoListVo;
import cn.cld.service.lianxi.LianxiDemoServiceApi;
import cn.cld.untils.CldCommonUntils;
import cn.cld.untils.DateTimeUtils;
import cn.cld.untils.StringUtils;
import org.apache.ibatis.session.RowBounds;

import javax.annotation.Resource;
import java.util.List;

public class LianxiDemoServiceApiImpl implements LianxiDemoServiceApi {

    @Resource
    private UserInfoMapper userInfoMapper;

    public PageQueryResult<UserInfo> queryUserInfo(UserInfoListVo params) {

        PageQueryResult<UserInfo> pageQueryResult =new PageQueryResult<UserInfo>();
        params.setPage(1);
        params.setRows(10);

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
        if(StringUtils.hasText(params.getIsUse()+"")){
            criteria.andIsUseEqualTo(params.getIsUse());
        }


        int count = userInfoMapper.countByExample(ue);
        pageQueryResult.setTotal(count);
        // 分页数据
        RowBounds rowBounds = new RowBounds((params.getPage() - 1) * params.getRows(), params.getRows());
        List<UserInfo> userInfos = userInfoMapper.selectByExampleWithRowbounds(ue, rowBounds);

        pageQueryResult.setRows(userInfos);

        return pageQueryResult;
    }

    @Override
    public MessageResult addUser(UserInfo vo) {
        MessageResult result = new MessageResult();
        //判断用户名是否存在
        UserInfoExample ue = new UserInfoExample();
        ue.createCriteria().andUserNameEqualTo(vo.getUserName());
        List<UserInfo> userInfos = userInfoMapper.selectByExample(ue);
        //已存在
        if(!userInfos.isEmpty()){
            result.setResult(false);
            result.setMessage("用户 "+vo.getUserName()+"已存在，请重新输入");
            return result;
        }
        //新增操作
        int i = userInfoMapper.insertSelective(vo);
        if(i==1){
            result.setMessage("用户 "+vo.getUserName()+"新增成功！");
            return result;
        }else {
            result.setMessage("用户 "+vo.getUserName()+"新增失败！");
            return result;
        }
    }

    @Override
    public MessageResult deleteUserInfo(List<UserInfo> userInfoListVo) {
        MessageResult result = new MessageResult();
        int m = userInfoListVo.size();
        int total =0;
        for(UserInfo userInfo:userInfoListVo){
            UserInfo ui = new UserInfo();
            ui.setIsUse(0);

            UserInfoExample uie = new UserInfoExample();
            uie.createCriteria().andUserIdEqualTo(userInfo.getUserId());

            int i = userInfoMapper.updateByExampleSelective(ui, uie);
            if(1==i){
                total++;
                }
        }
        if(m==total){
            result.setMessage("删除成功!");
            return result;
        }else {
            result.setResult(false);
            result.setMessage("删除失败！");
            return result;
        }
}




}
