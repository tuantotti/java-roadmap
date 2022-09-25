package com.java.concurrency.locks;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class BankUtils {
    private final Random rnd = new Random();

    public boolean transferMoney(Account fromAcct,
                                 Account toAcct,
                                 long amount,
                                 long timeout,
                                 TimeUnit unit) throws InterruptedException {
        long fixedDelay = getFixedDelayComponentNanos(timeout, unit);
        long randMod = getRandomDelayModulusNanos(timeout, unit);
        long stopTime = System.nanoTime() + unit.toNanos(timeout);
        while (true) {
            if (fromAcct.lock.tryLock()) {
                try {
                    if (toAcct.lock.tryLock()) {
                        try {
                            if (fromAcct.getBalanced() < amount)
                                throw new RuntimeException("Not enough money in your account");

                            fromAcct.minus(amount);
                            toAcct.plus(amount);
                        } finally {
                            toAcct.lock.unlock();
                        }
                    }
                } finally {
                    fromAcct.lock.unlock();
                }
            }

            if (System.nanoTime() < stopTime)
                return false;
            NANOSECONDS.sleep(fixedDelay + rnd.nextLong() % randMod);
        }
    }

    private long getRandomDelayModulusNanos(long timeout, TimeUnit timeUnit) {
        return 0;
    }

    private long getFixedDelayComponentNanos(long timeout, TimeUnit timeUnit) {
        return 0;
    }
}
