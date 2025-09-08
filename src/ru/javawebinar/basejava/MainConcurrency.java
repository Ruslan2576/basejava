package ru.javawebinar.basejava;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainConcurrency {
    private static final AtomicInteger atomicCounter = new AtomicInteger();
    public static final int THREADS_NUMBER = 10_000;
    private static int counter;
    private static Lock lock = new ReentrantLock();
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();

        new Thread(() -> System.out.println(Thread.currentThread().getName() +
                ", " + Thread.currentThread().getState())).start();

        System.out.println(thread0.getState());

        MainConcurrency mainConcurrency = new MainConcurrency();
        ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        // CompletionService completion = new ExecutorCompletionService(exec);

        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        // List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<Integer> future = exec.submit(() -> {
//            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                    // System.out.println(dtf.getLocale());
                }
                latch.countDown();
                return 0;
            });
            // completion.poll();

//            thread.start();
//            threads.add(thread);
        }
//
//        threads.forEach(t -> {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });

        latch.await(10, TimeUnit.SECONDS);
        exec.shutdown();
        System.out.println(counter);
        System.out.println(atomicCounter.get());
    }

    private void inc() {
        atomicCounter.incrementAndGet();
//        // synchronized (this) {
//        lock.lock();
//        try {
//            counter++;
//        } finally {
//            lock.unlock();
//        }
//       }
    }
}
