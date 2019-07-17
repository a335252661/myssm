package cn.cld.test.watch;

/**
 * @author by cld
 * @date 2019/7/11  10:15
 * @description:
 */
public class cldhashcode {
    public static void main(String[] args) {
        System.out.println("mm".hashCode());


        String xx = new String("mm");

        System.out.println(xx.hashCode());
        System.out.println(xx.equals("oo"));
    }
}
