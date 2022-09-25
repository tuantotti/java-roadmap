package com.java.concurrency.collection_framework;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class BlockingQueueExample {

    public static void main(String[] args) {
        BlockingQueue<Object> queue = new LinkedBlockingDeque<>();
        ExecutorService producerService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            producerService.submit(new Producer(queue));
        }

        ExecutorService consumerService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            consumerService.submit(new Consumer(queue));
        }
    }
}

class Producer implements Runnable {
    private BlockingQueue<Object> queue;

    public Producer(BlockingQueue<Object> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            Object object = getResource();

            try {
                queue.put(object);
                System.out.println("Produced resource - " + object + " - Queue size now = " + queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Object getResource() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new Object();
    }
}

class Consumer implements Runnable {
    private BlockingQueue<Object> queue;

    public Consumer(BlockingQueue<Object> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object o = queue.take();
                System.out.println("Consumed resource - " + o + " - Queue size now = " + queue.size());
                take(o);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void take(Object object) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}