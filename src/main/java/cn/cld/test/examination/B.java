package cn.cld.test.examination;

/**
 * @author by cld
 * @date 2019/8/7  21:56
 * @description:
 */
public class B {

    public  int m =0 ;
//    Integer
    @Deprecated
    public void fun(){
    }

    public static B t1 = new B();
    public B() {
        System.out.println(8888);
    }
    {
        System.out.println("构造块");
    }
    static
    {
        System.out.println("静态块");
    }
    public static void main(String[] args)
    {
        B t = new B();
    }
}
