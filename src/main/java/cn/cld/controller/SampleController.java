package cn.cld.controller;

import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("sample")
@SuppressWarnings(value={"deprecation","unchecked"})
public class SampleController {
    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @Resource
    private org.quartz.impl.StdScheduler schedulerFactoryBean;


    @RequestMapping("textBatch.htm")
    @ResponseBody
    public String textBatch(@RequestParam("jobKey") String jobKey){
        try {
            schedulerFactoryBean.triggerJob(new JobKey(jobKey));

        } catch (SchedulerException e) {
            logger.error("启动 batch 失败", e);
            return e.toString();
        }
        return "batch started.";
    }

}
