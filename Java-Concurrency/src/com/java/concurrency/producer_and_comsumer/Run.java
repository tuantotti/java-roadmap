package com.java.concurrency.producer_and_comsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Run {
    public static void main(String[] args) {
        Process process = new Process();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}

class Process {
    private static int UPPER = 5;
    private static int LOWER = 0;
    private int value = 0;
    private static Object lock = new Object();
    private List<Integer> list = new ArrayList<>();

    public void producer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == UPPER) {
                    System.out.println("Waiting for removing items...");
                    lock.wait();
                } else {
                    System.out.println("Adding " + value);
                    // postfix value++ : increment and return the value
                    // prefix ++value : return the value and increment
                    list.add(value++);
                    lock.notify();
                }
                Thread.sleep(1000);
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == LOWER) {
                    System.out.println("Waiting for adding items...");
                    value = 0;
                    lock.wait();
                } else {
                    System.out.println("Remove " + list.remove(list.size() - 1));
                    lock.notify();
                }
                Thread.sleep(1000);
            }
        }
    }


}
