package com.java.concurrency.dining_philosopher;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
    private Lock lock;
    private String id;

    public Chopstick(String id) {
        lock = new ReentrantLock();
        this.id = id;
    }

    public boolean pickUp(Philosopher philosopher) {
        if (lock.tryLock()) {

        }
        return false;
    }
}
