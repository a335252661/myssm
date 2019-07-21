package cn.cld.test.examination.OperaAndEquras;

/**
 * @author by cld
 * @date 2019/7/21  21:27
 * @description:
 */
public class Opera1 {
    public static void main(String[] args) {
        int q1 = 99;
        System.out.println(
                q1+"的二进制"+ Integer.toBinaryString(q1)
        );

        System.out.println(~20);
        System.out.println(99>>2);


        int v = 78;
        int v1 = v << 1;
        String sv = Integer.toBinaryString(v);
        String sv1 = Integer.toBinaryString(v1);
        System.out.println(sv);
        System.out.println(v+"带符号右移一位的二进制是"+sv1+",变为"+v1);

    }
}
