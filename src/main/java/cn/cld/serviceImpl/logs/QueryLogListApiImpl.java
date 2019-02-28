package cn.cld.serviceImpl.logs;

import cn.cld.dao.LogsDetailMapper;
import cn.cld.dao.LogsMapper;
import cn.cld.pojo.Logs;
import cn.cld.pojo.LogsDetail;
import cn.cld.pojo.LogsDetailExample;
import cn.cld.pojo.LogsExample;
import cn.cld.pojo.basic.PageQueryResult;
import cn.cld.pojo.logs.LogsDetailVo;
import cn.cld.pojo.logs.LogsListVo;
import cn.cld.service.logs.QueryLogListApi;
import org.apache.ibatis.session.RowBounds;

import javax.annotation.Resource;
import java.util.List;

public class QueryLogListApiImpl implements QueryLogListApi {
    @Resource
    private LogsMapper logsMapper;
    @Resource
    private LogsDetailMapper logsDetailMapper;
    @Override
    public PageQueryResult<Logs> queryLogs(LogsListVo params) {


        PageQueryResult<Logs> pageQueryResult= new PageQueryResult<>();

        LogsExample le = new LogsExample();
        //按照时间倒序排列，使得在页面上最近的时间在最上面显示
        le.setOrderByClause("time desc");

        int count = logsMapper.countByExample(le);

        pageQueryResult.setTotal(count);
        // 分页数据
        RowBounds rowBounds = new RowBounds((params.getPage() - 1) * params.getRows(), params.getRows());
        List<Logs> logs = logsMapper.selectByExampleWithRowbounds(le, rowBounds);

        pageQueryResult.setRows(logs);
        return pageQueryResult;
    }


    @Override
    public PageQueryResult<LogsDetail> queryLogDetailById(LogsDetailVo params) {
        PageQueryResult<LogsDetail> pageQueryResult= new PageQueryResult<>();

        LogsDetailExample lde = new LogsDetailExample();
        lde.createCriteria().andLogsIdEqualTo(params.getLogsId());

        int count = logsDetailMapper.countByExample(lde);

        pageQueryResult.setTotal(count);
        // 分页数据
        RowBounds rowBounds = new RowBounds((params.getPage() - 1) * params.getRows(), params.getRows());
        List<LogsDetail> logs = logsDetailMapper.selectByExampleWithRowbounds(lde, rowBounds);

        pageQueryResult.setRows(logs);
        return pageQueryResult;
    }
}
