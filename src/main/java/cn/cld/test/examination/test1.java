package cn.cld.test.examination;

import cn.cld.test.test;
import cn.cld.untils.CldCommonUntils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author by cld
 * @date 2019/7/20  17:38
 * @description:
 */
public class test1 {
    int b=20;
    final ArrayList list = new ArrayList();
    char m;
    public static void main(String[] args) throws Exception{
        test1 test = new test1();
        System.out.println(test.m);

        int a= '爱';
        char cha = (char)33;
        System.out.println("a="+a);
        System.out.println("char="+cha);

        float ee = (float) 20.2;
        double dd = 20;
        long ll = 20;
        int in = 20;
        System.out.println(dd==ll);//true
        System.out.println(in==ll);//true


        Properties properties = new Properties();
        int arr1[]={1,2,3};
        int arr2[]={1,2,3};
        System.out.println(Arrays.equals(arr1,arr2));//true

        int arr[][][][][][] = new int[9][][][][][];

//        System.out.println(90>>>3);
//        System.out.println(90>>3);
//
//        URL url = new URL("https://www.cnblogs.com/ntfblogs/p/11009920.html");
//        InputStream is = url.openStream();
//
//        File file = new File("C:\\Users\\Administrator\\Desktop\\do\\新建文本文档 (2).txt");
//        FileInputStream fileInputStream = new FileInputStream(file);
////        System.out.println(CldCommonUntils.InputStreamToByte(is).toString());
//        System.out.println(new String(CldCommonUntils.InputStreamToByte(fileInputStream),"GBK"));



    }
}
