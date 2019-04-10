package cn.cld.controller.webService;


import cn.cld.untils.DateTimeUtils;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("send")
public class SendTestData {

    private static final Logger logger = LoggerFactory.getLogger(SendTestData.class);

    /**
     * 接口接受数据
     * @param request
     * @param pon
     * @return
     */
    @RequestMapping("sendtest")
    @ResponseBody
    public String sendtest(HttpServletRequest request, HttpServletResponse pon){

        Date beg1 = new Date(System.currentTimeMillis());


        //需要请求过去的json数据
        String newdata  = "{mm:33}";
        String requestURL = "http://localhost:8087/receive/test";


        //okhttp3做请求类
        // 使用okhttp做httpclient请求类
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(2, TimeUnit.HOURS)//设置读取超时时间
                .writeTimeout(2, TimeUnit.HOURS)//设置写的超时时间
                .connectTimeout(2, TimeUnit.HOURS)//设置连接超时时间
                .build();

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, newdata);

        HttpUrl url = HttpUrl.parse(requestURL).newBuilder() //请求尾部链接
                .addQueryParameter("companygroupid", "01")
                .addQueryParameter("signature", "EDFCNAJSAUIDWUQIHZ")
                .build();

        //添加请求头部
        Request request2 = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                // 表示数据压缩
                .addHeader("Content-Encoding", "deflate")
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request2);

        Response response = null;
        String responseStr = "";
        try {
            // 开始请求
            response = call.execute();
            responseStr = response.body().string();
            logger.info("接口调用:url：" + url + " response：" + responseStr);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Date end1 = new Date(System.currentTimeMillis());
        logger.info("程序总用时："+DateTimeUtils.getTimeDifference(end1,beg1));

        return "ok";
    }


}
