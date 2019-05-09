package cn.cld.test;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class cld3  {
    public static void main(String[] args) throws Exception{

        String xx = new BASE64Encoder().encodeBuffer("systemccc".getBytes());
        System.out.println(new BASE64Encoder().encodeBuffer("systemccc".getBytes()));

        System.out.println(new String(new BASE64Decoder().decodeBuffer(xx)));


    }
}
