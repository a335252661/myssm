package cn.cld.test.examination.ThreadAndRunnable;

/**
 * @author by cld
 * @date 2019/8/3  12:25
 * @description:
 */
public class SynchronizedTest2 {
    public static void main(String[] args) {
        final B b = new B();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    b.mB2("mmm");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    b.mB2("qqq");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();
    }
}

     class B {
        //修饰this拿到对象锁
        public void mB2(String name) throws InterruptedException {
            synchronized (Thread.class) {
                for (int i = 0; i < 6000; i++) {
                    System.out.println(name);
                }
            }
        }

    }
