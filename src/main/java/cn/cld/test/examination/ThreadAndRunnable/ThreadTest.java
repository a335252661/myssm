package cn.cld.test.examination.ThreadAndRunnable;

/**
 * @author by cld
 * @date 2019/8/3  10:22
 * @description:
 */
public class ThreadTest extends Thread{

    @Override
    public void run() {
        System.out.println(1);
    }

    public static void main(String[] args) {
        ThreadTest thread1 = new ThreadTest();
        thread1.start();
    }
}
