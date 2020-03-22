package cn.cld.test.examination.ThreadAndRunnable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author by cld
 * @date 2019/8/3  10:33
 * @description:
 */
public class CallableTest implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(88);
        return 99;
    }

    public static void main(String[] args) {
        CallableTest callableTest = new CallableTest();
        FutureTask<Integer> futureTask = new FutureTask<>(callableTest);
        Thread thread = new Thread(futureTask);
        thread.start();
    }

}
