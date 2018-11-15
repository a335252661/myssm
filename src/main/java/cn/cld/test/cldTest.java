package cn.cld.test;

import cn.cld.untils.DateTimeUtils;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class cldTest {

public static void main(String[] args) {
    String dateStr = "2018-11-11";

    Date date = DateTimeUtils.parseStr(dateStr);
    System.out.println(date);
}

}
