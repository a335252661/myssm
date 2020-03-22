package cn.cld.test.examination.ThreadAndRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author by cld
 * @date 2019/8/9  10:04
 * @description: 通过线程池创建线程
 */
public class ExecutorstThread {
    public void fun1(){
        ExecutorService cachedThreadPool  = Executors.newCachedThreadPool();
        //创建定长的线程池，超出的线程会在队列中等待。
//        ExecutorService cachedThreadPool = Executors.newFixedThreadPool(3);
        //按顺序来执行线程任务   但是不同于单线程，这个线程池只是只能存在一个线程，这个线程死后另外一个线程会补上。
//        ExecutorService cachedThreadPool = Executors.newSingleThreadExecutor();
        for(int i=0;i<10;i++){
            final int index = i;
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }
    }
    public void fun2(){
        //创建一个定长线程池，支持定时及周期性任务执行。
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++) {
            scheduledThreadPool.schedule(new Runnable() {
                public void run() {
                    System.out.println("delay 3 seconds");
                }
            }, 3, TimeUnit.SECONDS);
        }
    }

    public static void main(String[] args) {
        ExecutorstThread executorstThread = new ExecutorstThread();
        executorstThread.fun1();


    }
}
