package cn.cld.test.examination.DesignPattern.lnstance;

/**
 * @author by cld
 * @date 2019/8/8  14:12
 * @description: 单例模式
 */
public class lnstance {
    private  static final lnstance ins = new lnstance();
    private  lnstance( ) {
    }
    public static  lnstance getInstance(){
        ins.fun();
        return ins;
    }
    public void fun(){};
}
