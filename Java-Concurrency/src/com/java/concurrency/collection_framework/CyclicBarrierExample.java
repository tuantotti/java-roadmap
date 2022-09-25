package com.java.concurrency.collection_framework;

import java.util.concurrent.CyclicBarrier;

class WorkerThread implements Runnable {

    private int id;
    private CyclicBarrier cyclicBarrier;

    public WorkerThread(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
    }
}

public class CyclicBarrierExample {
}
