package com.java.concurrency.collection_framework;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * - Main thread start
 * - Create CountDownLatch for N threads
 * - Create and start N threads
 * - Main thread wait on latch
 *      - N threads completes their tasks and count down the latch
 * - Main thread resume execution
 */
class Worker implements Runnable {
    private int id;
    private CountDownLatch countDownLatch;

    public Worker(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        doWork();
        countDownLatch.countDown();// when count = 0 --> await method return immediately and then execute other task
        System.out.println("-latch " + countDownLatch.getCount());
    }

    private void doWork() {
        try {
            System.out.println("Worker with id " + id + " are working..." + "- thread name: " + Thread.currentThread().getName());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class CountDownLatchExample {


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Worker(i + 1, countDownLatch));
        }

        try {
            // this method blocks until countDown return 0 and when count=0 this method returns immediately and runs the below code
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Done job....");
        executorService.shutdown();
    }
}
