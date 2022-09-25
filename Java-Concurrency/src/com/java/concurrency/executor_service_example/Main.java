package com.java.concurrency.executor_service_example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * https://medium.com/codex/executorservice-internal-working-in-java-7b286882f54e
 * assign lock for each segment instead of using a single lock
 */
public class Main {
    public static void main(String[] args) {

    }

    /**
     * It shuts down the executor service and waits for some time for submitted tasks to complete.
     * If the running tasks do not complete in certain time, they are terminated forcefully.
     * @param pool the thread pool
     */
    static void shutdownAndAwaitTermination(ExecutorService pool) {
        // Disable new tasks from being submitted
        pool.shutdown();
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                // Cancel currently executing tasks forcefully
                pool.shutdownNow();
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ex) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}

/**
 * to shut down executor service the below link is the best practice
 * https://howtodoinjava.com/java/multi-threading/executorservice-shutdown/#1-difference-between-shutdown-shutdownnow-and-awaittermination
 */