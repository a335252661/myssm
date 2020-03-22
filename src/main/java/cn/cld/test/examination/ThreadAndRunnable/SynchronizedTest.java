package cn.cld.test.examination.ThreadAndRunnable;

/**
 * @author by cld
 * @date 2019/8/3  11:48
 * @description:
 */
public class SynchronizedTest implements Runnable{


    @Override
    public  void run() {

        synchronized (this){
            System.out.println("我是对象锁的代码块形式。我叫" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束。");
        }

    }
    public static void main(String[] args) {
        SynchronizedTest test2 = new SynchronizedTest();

        Thread thread1 = new Thread(test2);
        Thread thread2 = new Thread(test2);

        thread1.start();
        thread2.start();

    }
}
