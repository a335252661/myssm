package cn.cld.controller.webService;

import cn.cld.untils.CldCommonUntils;
import cn.cld.untils.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("receive")
public class AcceptTestData {

    private static final Logger logger = LoggerFactory.getLogger(AcceptTestData.class);


    /**
     * 接口接受数据
     * @param request
     * @param pon
     * @return
     */
    @RequestMapping("test")
    @ResponseBody
    public String PurchaseOrder(HttpServletRequest request, HttpServletResponse pon){

        Date beg1 = new Date(System.currentTimeMillis());

        //获取请求头部数据
        String header = request.getHeader("Content-Type");
        logger.info("头部 Content-Type ："+header);

        //获取请求中参数
        String signature = request.getParameter("signature");
        logger.info("参数 signature ："+signature);

        String data = "";
        try{
            ServletInputStream inputStream = request.getInputStream();
            //流信息中所有字节数组
            byte[] bytes =CldCommonUntils.InputStreamToByte(inputStream);
            //字节数组转String
            data = new String(bytes,"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }

        //获取接口数据
        logger.info("获取到的接口数据 ："+ data);
        Date end1 = new Date(System.currentTimeMillis());
        logger.info("程序总用时："+DateTimeUtils.getTimeDifference(end1,beg1));

        return data;
    }
}
