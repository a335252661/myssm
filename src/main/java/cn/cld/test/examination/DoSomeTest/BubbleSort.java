package cn.cld.test.examination.DoSomeTest;

import java.util.Arrays;

/**
 * @author by cld
 * @date 2019/8/12  20:46
 * @description: 冒泡排序
 */
public class BubbleSort {
    
    
    public void fun(){
        int []arr = {3,6,1,8,9};
        for(int i=0;i<arr.length-1;i++){
            for(int m=0;m<arr.length-1-i;m++){
                if(arr[m]>arr[m+1]){
                    int tem=arr[m];
                    arr[m]=arr[m+1];
                    arr[m+1]=tem;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        new BubbleSort().fun();
    }
}
