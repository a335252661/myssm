package cn.cld.test;

import redis.clients.jedis.Jedis;

public class cldTestRedis {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("query"));
    }
}
