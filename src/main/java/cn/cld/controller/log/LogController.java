package cn.cld.controller.log;

import cn.cld.pojo.Logs;
import cn.cld.pojo.basic.PageQueryResult;
import cn.cld.pojo.logs.LogsListVo;
import cn.cld.service.logs.QueryLogListApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("queryLogList")
public class LogController {

    @Resource
    private QueryLogListApi queryLogListApi;

    @RequestMapping("")
    public String queryLog(ModelAndView mav, HttpServletRequest request){
        return  "queryLogList";
    }

    @RequestMapping("query")
    @ResponseBody
    public PageQueryResult<Logs> query(LogsListVo logsListVo, HttpServletRequest request){

        PageQueryResult<Logs> resultLogs = queryLogListApi.queryLogs(logsListVo);

        System.out.println(resultLogs);
        return resultLogs ;
    }
}
