package cn.cld.test;

import cn.cld.pojo.basic.MessageResult;
import cn.cld.pojo.basic.testIOC;
import cn.cld.pojo.lianxi.UserInfoListVo;
import cn.cld.service.lianxi.LianxiDemoServiceApi;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ctxgetBean {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("service-config.xml");
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        LianxiDemoServiceApi lianxiDemoServiceApi=(LianxiDemoServiceApi)ctx.getBean("lianxiDemoServiceApi");
//        String path2 = "";
//        UserInfoListVo userInfoListVo = new UserInfoListVo();
//        MessageResult messageResult = lianxiDemoServiceApi.downLoadTxt(userInfoListVo,path2);



        testIOC testIOC=(testIOC)ctx.getBean("testIOC");
        System.out.println(testIOC.getSize());
        testIOC.setSize("99");
        testIOC testIOC2=(testIOC)ctx.getBean("testIOC");
        System.out.println(testIOC2.getSize());

        //第二种加载方式
        ApplicationContext context = new AnnotationConfigApplicationContext(testIOC.class);
        testIOC ioc=(testIOC)context.getBean("testIOC");
        System.out.println(ioc.getSize());


    }
}
