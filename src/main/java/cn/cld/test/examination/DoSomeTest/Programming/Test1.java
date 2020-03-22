package cn.cld.test.examination.DoSomeTest.Programming;

import org.apache.poi.util.StringUtil;

/**
 * @author by cld
 * @date 2019/8/14  16:52
 * @description:
 */
public class Test1 {

    public void fun(Test1Instance xx){

    }

    public static void main(String[] args) {

        String str = "h11djskah22udnl33xksa";

        char[] chars = str.toCharArray();
        String arr[] = {"wo","在","哪里"};

        System.out.println(chars);
        System.out.println(new String(chars));
        StringBuilder builder = new StringBuilder(str);
        System.out.println(builder.reverse());

        System.out.println(str.replace("1","mm"));

    }
}
