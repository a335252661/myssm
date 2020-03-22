package cn.cld.test.mapAndListTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by cld
 * @date 2019/8/20  13:46
 * @description:
 */
public class TestList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(2);
        list.add("guan");
        list.add("bao");
        String[] array = new String[list.size()];
        array = list.toArray(array);
    }
}
