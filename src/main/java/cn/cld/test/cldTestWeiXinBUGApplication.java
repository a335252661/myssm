package cn.cld.test;

import cn.cld.untils.HttpClientUtils;
import com.alibaba.fastjson.JSONObject;


public class cldTestWeiXinBUGApplication {
    public static void main(String[] args) {

        //企业微信id
        String corpid="wwcda20ad5423e09b1";

        //bug应用的corpsecret
        String corpsecret = "v_sWaLf1a-gcNj6JsvBkTpJNVuGFbYu1QQxSlL8PZUU";

        String secret = "qHToh622zGu94dqbI4tsGGHSeNJyyO1iOhz1Lxo3yBM";


        String acceptAccessTokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+corpid
                +"&corpsecret="+corpsecret;

        String s = HttpClientUtils.sendPostOrGet(acceptAccessTokenUrl, "");

        JSONObject jsonObject = JSONObject.parseObject(s);
        String access_token = jsonObject.getString("access_token");


        System.out.println(access_token);
//TyxWXhqx91APPHQdn4eEHnc9m_CSa4gUWKDxq_427acmm-d6KuiK8doG1MCvwG89GrJrHliCu7
// RgcOdmB6Maef4YjfQNXA-rxEg_zhrM5QwbsOyvilLyOmlYq2NGPJ_KabIFSfJlKjaKZk4dQc0W
// LmIy7BuVQqBUu75ovrBR-lqKXdu3uG3VnEaPsn60umEpkKQ5E1YVAFJvG6jdrpREKQ
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
