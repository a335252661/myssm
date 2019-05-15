package cn.cld.controller;

import cn.cld.pojo.basic.testIOC;
import org.apache.http.HttpRequest;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
@RequestMapping("sample")
@SuppressWarnings(value={"deprecation","unchecked"})
public class SampleController {
    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @Resource
    private org.quartz.impl.StdScheduler schedulerFactoryBean;

    @Resource(name="redisTemplate")
    protected ListOperations<String, String> redis;

    @Autowired
    ApplicationContext applicationContext;


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

    @RequestMapping("redis")
    @ResponseBody
    public String redis(HttpServletRequest request){
        RedisOperations<String, String> operations = redis.getOperations();
        RedisSerializer<?> keySerializer = operations.getKeySerializer();
        System.out.println(keySerializer);
        Set<String> query = operations.keys("query");
        System.out.println(query);
//        return redis.leftPop("query");
        return "ok";
    }

    /**
     * 通过applicationContext获取bean
     * @param request
     * @return
     */
    @RequestMapping("getBean")
    @ResponseBody
    public String getBean(HttpServletRequest request){
        testIOC te =  (testIOC)applicationContext.getBean("testIOC");
        return te.getSize();
    }

}
