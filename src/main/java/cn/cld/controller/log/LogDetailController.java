package cn.cld.controller.log;

import cn.cld.pojo.LogsDetail;
import cn.cld.pojo.basic.PageQueryResult;
import cn.cld.pojo.logs.LogsDetailVo;
import cn.cld.service.logs.QueryLogListApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("queryLogDetail")
public class LogDetailController {

    @Resource
    private QueryLogListApi queryLogListApi;

    @RequestMapping("")
    public ModelAndView queryLog(ModelAndView mav, HttpServletRequest request ,Integer id){
        System.out.println(id+"______________________________");
        mav.addObject("logsId" , id);
        mav.setViewName("queryLogDetail");
        return  mav;
    }

    @RequestMapping("query")
    @ResponseBody
    public PageQueryResult<LogsDetail> query(LogsDetailVo logsDetail){

        PageQueryResult<LogsDetail> resultLogs = queryLogListApi.queryLogDetailById(logsDetail);

        System.out.println(resultLogs);
        return resultLogs ;
    }

}
