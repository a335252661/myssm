package cn.cld.test.mapAndListTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author by cld
 * @date 2019/8/2  16:49
 * @description:
 */
public class mapTest extends mapTest2{
    public static void main(String[] args) {
        mapTest mm = new mapTest();
        System.out.println(mm.a+1);
        mm.fun();
        Map<String,String> map = new HashMap<>();
        map.put("11","22");
        map.put("12","22");
        map.put("13","23");
        map.put("14","24");
        Iterator<Map.Entry<String, String>> iterator1 = map.entrySet().iterator();
        while (iterator1.hasNext()){
            Map.Entry<String, String> next = iterator1.next();
            next.getKey();
            next.getValue();
            System.out.println(next);
        }

        Iterator<String> iterator = map.keySet().iterator();

        while (iterator.hasNext()){
            
        }

        String q1 = "Aa";
        String q2 = "BB";
        System.out.println(q1.hashCode());
        System.out.println(q2.hashCode());
        System.out.println(q1.equals(q2));
        System.out.println(100>>1);
        System.out.println(100>>2);
        System.out.println(100>>3);
        System.out.println(100>>4);
        System.out.println(Integer.toBinaryString(3<<4));
        System.out.println(100/16);
    }

    @Override
    void fun() {
        mapTest mm = new mapTest();
        System.out.println(mm.a);
    }

    @Override
    public void fun1() {

    }

    @Override
    public void fun3() {

    }
}
