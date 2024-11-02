package ru.mfti.atp.sem8;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentCounterFutures {
    private static final AtomicInteger x = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<Integer> task = () -> {
            System.out.println("Start thread " + Thread.currentThread().getName());
            for (int i = 0; i < 1_000_000; i++) {
                x.incrementAndGet();
            }
            return x.get();
        };

        Runnable runnableTask = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                x.incrementAndGet();
                // read x        1    1
                // x++           2    2
                // write x       2    2
            }
            System.out.println("thread end");
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);

//        Future<Integer> future1 = executorService.submit(runnableTask);
//        Future<Integer> future2 = executorService.submit(runnableTask);
        Future<?> future1 = executorService.submit(runnableTask);
        Future<?> future2 = executorService.submit(runnableTask);

//        System.out.println("result1 = " + future1.get());
//        System.out.println("result2 = " + future2.get());

        future1.get();
        future2.get();

        System.out.println("x = " + x.get());

        executorService.shutdown();
    }
}
