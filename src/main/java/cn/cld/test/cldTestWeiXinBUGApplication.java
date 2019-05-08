package cn.cld.test;

import cn.cld.untils.HttpClientUtils;
import com.alibaba.fastjson.JSONObject;


public class cldTestWeiXinBUGApplication {
    public static void main(String[] args) {

        //企业微信id
        String corpid="wwcda20ad5423e09b1";

        //bug应用的corpsecret
        String corpsecret = "v_sWaLf1a-gcNj6JsvBkTpJNVuGFbYu1QQxSlL8PZUU";

        String acceptAccessTokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+corpid
                +"&corpsecret="+corpsecret;

        String s = HttpClientUtils.sendPostOrGet(acceptAccessTokenUrl, "");

        JSONObject jsonObject = JSONObject.parseObject(s);
        String access_token = jsonObject.getString("access_token");

        String sendMessurl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="+access_token;

        String sendJson = "{\n" +
                "   \"touser\" : \"ChengLiuDe\",\n" +
                "   \"msgtype\" : \"text\",\n" +
                "   \"agentid\" : 1000002,\n" +
                "   \"text\" : {\n" +
                "       \"content\" : \"我是测试消息\"\n" +
                "   },\n" +
                "   \"safe\":0\n" +
                "}";

        HttpClientUtils.sendPostOrGet(sendMessurl,sendJson);

    }

}
