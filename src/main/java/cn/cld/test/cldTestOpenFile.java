package cn.cld.test;

import cn.cld.untils.CldCommonUntils;

import java.awt.*;
import java.io.File;
import java.io.InputStream;

public class cldTestOpenFile {

    public static void main(String[] args) throws Exception{
        //方式1
        //打开文件
//        Desktop.getDesktop().open(new File("C:\\tmp\\ppp.jpg"));

//方式2
//        Process exec = Runtime.getRuntime().exec("cmd /c start C:\\tmp\\ppp.jpg");
//        int i = exec.exitValue();
//        System.out.println(i);

        //使用cmd命令并输出
        Process exec = Runtime.getRuntime().exec("cmd /c ipconfig");
        InputStream inputStream = exec.getInputStream();
        byte[] bytes = CldCommonUntils.InputStreamToByte(inputStream);
        String data = new String(bytes,"GBK");
        System.out.println(data);
    }
}
