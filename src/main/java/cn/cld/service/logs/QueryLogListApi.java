package cn.cld.service.logs;

import cn.cld.pojo.Logs;
import cn.cld.pojo.LogsDetail;
import cn.cld.pojo.basic.PageQueryResult;
import cn.cld.pojo.logs.LogsDetailVo;
import cn.cld.pojo.logs.LogsListVo;

public interface QueryLogListApi {
    /**
     * 查询日志主表
     * @param logs
     * @return
     */
    PageQueryResult<Logs> queryLogs(LogsListVo logs);

    /**
     * 查询日志详细
     * @param LogsDetail
     * @return
     */
    PageQueryResult<LogsDetail> queryLogDetailById(LogsDetailVo LogsDetail);
}
