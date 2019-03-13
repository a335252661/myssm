package cn.cld.serviceImpl.lianxi;

import cn.cld.dao.MyUserMapper;
import cn.cld.dao.UserInfoMapper;
import cn.cld.pojo.LogsDetail;
import cn.cld.pojo.UserInfo;
import cn.cld.pojo.UserInfoExample;
import cn.cld.pojo.basic.MessageResult;
import cn.cld.pojo.basic.PageQueryResult;
import cn.cld.pojo.lianxi.UserInfoListVo;
import cn.cld.service.lianxi.LianxiDemoServiceApi;
import cn.cld.service.logs.AddLogsApi;
import cn.cld.untils.CldCommonUntils;
import cn.cld.untils.DateTimeUtils;
import cn.cld.untils.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class LianxiDemoServiceApiImpl implements LianxiDemoServiceApi {

    protected static final Logger logger = LoggerFactory.getLogger(LianxiDemoServiceApiImpl.class);

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private MyUserMapper myUserMapper;
    @Resource
    private AddLogsApi addLogsApi;

    public PageQueryResult<UserInfo> queryUserInfo(UserInfoListVo params) {

        PageQueryResult<UserInfo> pageQueryResult =new PageQueryResult<UserInfo>();
//        params.setPage(1);
//        params.setRows(10);

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

    @Override
    public MessageResult userListUplode(List<Map<String, String>> myData ,int logsId) {

        LogsDetail ld = new LogsDetail();
        ld.setLogsId(logsId);


        MessageResult result = new MessageResult();
        int total = 0;
        int updatetotal=0;
        for(Map<String, String> map:myData){
            String userName = map.get("用户名");
            String passWord = map.get("用户密码");

            UserInfo ui = new UserInfo();
            ui.setUserName(userName);
            ui.setPassWord(passWord);

            //判断该用户是否存在
            UserInfoExample uie =new UserInfoExample();
            uie.createCriteria().andUserNameEqualTo(userName);
            List<UserInfo> userInfos = userInfoMapper.selectByExample(uie);
            if(userInfos.isEmpty()){
                //不存在
                 userInfoMapper.insertSelective(ui);
                total++;
            }else {
                //存在则更新
                 userInfoMapper.updateByExampleSelective(ui,uie);
                updatetotal++;
            }



        }
        result.setMessage("新增用户"+total+"位，更新用户"+updatetotal+"位！");

        ld.setMessage("新增用户"+total+"位，更新用户"+updatetotal+"位！");
        ld.setOperatorTrans(2);//处理成功
        addLogsApi.insertLogsDetail(ld);

        return result;
    }


    //导出操作
    @Override
    public String[][] userListExport(UserInfoListVo userInfoListVo) {
       // List<List<Object>> data = new ArrayList<List<Object>>();

        List<UserInfo> userInfoList = myUserMapper.queryUser(userInfoListVo);

        String[][] strings = new String[userInfoList.size()][5];
        int i = 0;
        for(UserInfo vo :userInfoList){
            String[] arr = {
                    vo.getUserId()+"",
                    vo.getUserName(),
                    vo.getPassWord(),
                    vo.getIsUse()+"",
                    CldCommonUntils.dataToString(vo.getCreatDate(),CldCommonUntils.yyyy_MM_dd_HH_mm_ss)
            };
            strings[i]=arr;
            i++;
        }
        return strings;
    }


    /**
     * 打印预览
     * @param userId
     * @return
     */
    @Override
    public MessageResult userListPrint(int userId) {
        MessageResult result  = new MessageResult();
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        result.setData(userInfo);
        return result;
    }

    /**
     * TXT下载
     * @param userInfoListVo
     * @param path2
     * @return
     */
    @Override
    public MessageResult downLoadTxt(UserInfoListVo userInfoListVo, String path2) {
        MessageResult result  = new MessageResult();
        result.setResult(true);
        //删除之前生成的文件
        CldCommonUntils.delete(new File(path2),"txt");

        List<String> detailList = new ArrayList<>();


        //需要写入的信息
        detailList.add("用户id ："+userInfoListVo.getUserId());
        detailList.add("用户名 ："+userInfoListVo.getUserName());
        detailList.add("密  码 ："+userInfoListVo.getPassWord());
        detailList.add("end!!");



        String format = DateTimeUtils.format(new Date(), DateTimeUtils.YY_MM_DD_HH_mm);
        String name = "用户信息".concat(format).concat(".txt");
        String path = path2+"\\"+name;
        logger.info("生成的txt文件所在位置："+path);

        try {
            //指定到具体文件
            CldCommonUntils.txtWriter(path,detailList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.setRemarks(path);
        return result;
    }

    /**
     * txt下载不生成临时文件
     * @param userInfoListVo
     * @return
     */
    @Override
    public MessageResult downLoadTxt2(UserInfoListVo userInfoListVo) {
        MessageResult result  = new MessageResult();
        result.setResult(true);

        String data = "用户id ："+userInfoListVo.getUserId()
                +"\r\n"+
                "用户名 ："+userInfoListVo.getUserName()
                +"\r\n"+
                "密  码 ："+userInfoListVo.getPassWord()
                +"\r\n"+
                "end!!";



        String format = DateTimeUtils.format(new Date(), DateTimeUtils.YY_MM_DD_HH_mm);
        String name = "用户信息".concat(format).concat(".txt");


        result.setData(data);
        result.setRemarks(name);
        return result;
    }


}
