package ru.mfti.atp.sem8;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentCounterThreads {
    static int x = 0;
//    static AtomicInteger x = new AtomicInteger(0);
    static Lock lock = new ReentrantLock();
    private static final Object monitor = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                increment();
                // read x        1    1
                // x++           2    2
                // write x       2    2
            }
            System.out.println("thread end");
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("x=" + x);
    }

//    private static synchronized void increment() {
//        x++;
//    }

    private static void increment() {
        synchronized (monitor) {
            x++;
        }
    }

//    private static void increment() {
//        x.incrementAndGet();
//    }

//    private static void increment() {
//        lock.lock();
//        // critical section
//        x++;
//        //
//        lock.unlock();
//    }
}
