package com.java.concurrency.locks;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Run {
    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
        Thread t1 = new Thread(() -> {
            try {
                lockTest.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                lockTest.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}

class LockTest {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce() throws InterruptedException {
        // acquire the lock
        lock.lock();
        System.out.println("Produce method is running ");
        condition.await();
        System.out.println("Produce method again");
        lock.unlock(); // release the lock
    }

    public void consume() throws InterruptedException {
        Thread.sleep(2000);
        lock.lock();
        System.out.println("Consume method is running");
        condition.signal();
        Thread.sleep(3000);
        System.out.println("Consume method again");
        lock.unlock();
    }
}
