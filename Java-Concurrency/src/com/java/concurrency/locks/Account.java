package com.java.concurrency.locks;

import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private UUID id;
    private String name;
    private long balanced;
    public Lock lock = new ReentrantLock();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void minus(long amount) {
        balanced -= amount;
    }

    public void plus(long amount) {
        balanced += amount;
    }

    public long getBalanced() {
        return balanced;
    }

    public void setBalanced(long balanced) {
        this.balanced = balanced;
    }
}
