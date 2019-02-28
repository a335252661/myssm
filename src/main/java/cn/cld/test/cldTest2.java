package cn.cld.test;

import cn.cld.untils.CldCommonUntils;
import org.apache.commons.codec.binary.Base64;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class cldTest2 {
    public static void main(String[] args) {

        String xx  =CldCommonUntils.encodeMD5("PRO_FL_WMS");
        System.out.println(xx);
    }
}
