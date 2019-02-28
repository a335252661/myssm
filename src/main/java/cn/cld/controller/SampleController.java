package cn.cld.controller;

import cn.cld.untils.PropertiesUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Properties;

@Controller
@RequestMapping("sample")
@SuppressWarnings(value={"deprecation","unchecked"})
public class SampleController {
    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @Resource
    private org.quartz.impl.StdScheduler schedulerFactoryBean;
    @Autowired
    private Environment env;

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

    @RequestMapping("env")
    @ResponseBody
    public String env(){
        String xx = env.getProperty("Content-Encoding");
        System.out.println("xx : "+xx);
        System.out.println("9999999999");


        String miSkey = PropertiesUtil.getValue("MISkey");
        System.out.println(miSkey);

        return "ok";
    }
}
