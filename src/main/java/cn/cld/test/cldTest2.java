package cn.cld.test;

import java.util.ArrayList;

public class cldTest2 {
    public static void main(String[] args) {
        int[] arr1 = {1};
        int[] arr2 = {1,2};
        int[] arr3 = {1,2,3,4,5,6,7,8,9};

        int[][] pp = {arr1,arr2,arr3};

//        int[] arr4 = new int[9];
//        arr4[0]=2;
//        arr4[1]=3;
//        for(int x : arr4){
//            System.out.println(arr4[x]);
//        }






        int[][] arrlist = {{1},{1,2},{1,2,3}};

        int[][] arrlist2 = new int[3][10];


        for(int i=0;i<pp.length;i++){
            for(int j=0;j<pp[i].length;j++){
                System.out.print(pp[i][j]);
            }
            System.out.println();
        }


    }
}
