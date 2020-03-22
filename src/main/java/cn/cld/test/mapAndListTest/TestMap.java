package cn.cld.test.mapAndListTest;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author by cld
 * @date 2019/8/20  13:52
 * @description:
 */
public class TestMap {

    public static void main(String[] args) {
        // 创建一个Map
        Map<String, Object> infoMap = new HashMap<>();
        infoMap.put("name", "Zebe");
        infoMap.put("site", "www.zebe.me");
        infoMap.put("email", "zebe@vip.qq.com");
// 传统的Map迭代方式
        for (Map.Entry<String, Object> entry : infoMap.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
        }
// JDK8的迭代方式
        infoMap.forEach((key, value) -> {
            System.out.println(key + "：" + value);
        });

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        list.forEach(n -> System.out.println(n));

//     Function<Integer,Integer> mm= (Integer x,Integer y) -> {x + y};
        BiConsumer<Integer, String> b = (Integer x, String y) -> System.out.println(x + " : " + y);
//        BiConsumer<Integer, String> b = (Integer x, String y) -> x+y;
        ConcurrentHashMap cc = new ConcurrentHashMap<>();
    }
}
