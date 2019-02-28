package cn.cld.serviceImpl.logs;

import cn.cld.dao.LogsDetailMapper;
import cn.cld.dao.LogsMapper;
import cn.cld.pojo.Logs;
import cn.cld.pojo.LogsDetail;
import cn.cld.pojo.LogsExample;
import cn.cld.service.logs.AddLogsApi;
import cn.cld.untils.DateTimeUtils;
import cn.cld.untils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class AddLogsApiImpl implements AddLogsApi {

    @Resource
    private LogsMapper logsMapper;
    @Resource
    private LogsDetailMapper logsDetailMapper;
    /**
     * 插入日志主表
     * @param operator 操作人
     * @param operatorType 操作类型
     * @param operatorObject 操作对象
     * @return
     */
    @Transactional
    @Override
    public int insertLogs(int operator, String operatorType, String operatorObject) {

        Date date =new Date();
        Date dateWithoutTime = DateTimeUtils.getDateWithoutTime(date);

        Logs ll = new Logs();
        ll.setOperator(operator);
        ll.setOperatorType(operatorType);
        ll.setOperatorObject(operatorObject);
        ll.setTime(dateWithoutTime);
        ll.setOperatorTrans(0);//处理中

        int i = logsMapper.insertSelective(ll);
        if(1!=i){
            throw  new RuntimeException("插入日志主表失败。");
        }
        LogsExample le = new LogsExample();
        le.createCriteria().andTimeEqualTo(dateWithoutTime);
        List<Logs> logs = logsMapper.selectByExample(le);
        if(logs.size()==0){
            throw  new RuntimeException("返回日志主表id失败");
        }

        return logs.get(0).getId();
    }

    @Override
    public void insertLogsDetail(LogsDetail ld) {
        //新增日志详细表信息
        ld.setOperatorTime(new Date());
        logsDetailMapper.insertSelective(ld);

        //更新主表处理状态
        if(null!=ld.getOperatorTrans()){
            Logs ll = new Logs();
            ll.setOperator(ld.getOperator());
            ll.setOperatorTrans(ld.getOperatorTrans());

            LogsExample le = new LogsExample();
            le.createCriteria().andIdEqualTo(ld.getLogsId());

            logsMapper.updateByExampleSelective(ll,le);
        }


    }
}
