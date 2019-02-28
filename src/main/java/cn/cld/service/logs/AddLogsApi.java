package cn.cld.service.logs;

import cn.cld.pojo.LogsDetail;

public interface AddLogsApi {

    //插入日志主表
    int insertLogs(int operator, String operatorType, String operatorObject);

    //插入日志详细表并更新主表处理状态
    void insertLogsDetail(LogsDetail ld);
}
