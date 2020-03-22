package cn.cld.test.examination.ThreadAndRunnable;

/**
 * @author by cld
 * @date 2019/8/3  10:28
 * @description:
 */
public class RunnableTest implements Runnable{
    @Override
    public void run() {
        System.out.println(22);
    }

    public static void main(String[] args) {
        RunnableTest runnableTest = new RunnableTest();
        Thread thread = new Thread(runnableTest);
        thread.start();
    }
}
