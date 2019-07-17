package cn.cld.test;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.Arrays;
import java.util.List;

public class cld3  {
    public static void main(String[] args) throws Exception{
        Integer[] array = {15,1,7,2};

        System.out.println(array.length);
        int mm = 0;
        for(int i=0 ; i<array.length-1;i++){
           for(int j=0;j<array.length-i-1;j++)
            if(array[j]>array[j+1]){
                //交换
                mm=array[j];
                array[j]=array[j+1];
                array[j+1] = mm;
            }
        }
        Arrays.asList(array);
        System.out.println(Arrays.asList(array));
    }
}
