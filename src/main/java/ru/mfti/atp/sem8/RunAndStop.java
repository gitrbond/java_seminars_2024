package ru.mfti.atp.sem8;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class RunAndStop {
//    private static AtomicBoolean stopRunning = new AtomicBoolean(false);
    private static boolean stopRunning = false;

//    Callable<Void> callable = () -> {
//        int x = 0;
//        while (!stopRunning) {
//            x++;
//        }
//        System.out.println("Thread stopped, x=" + x);
//        return null;
//    };

    static Runnable runnable = () -> {
        int x = 0;
        while (!isStopRunning()) {
            x++;
        }
        System.out.println("Thread stopped, x=" + x);
    };

    public static synchronized boolean isStopRunning() {
        return stopRunning;
    }

    public static synchronized void stop() {
        stopRunning = true;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<?> future = executorService.submit(runnable);

        Thread.sleep(1);

//        stopRunning.set(true);
        stop();
        future.get();

        executorService.shutdown();
    }
}
