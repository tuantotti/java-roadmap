package com.java.concurrency.semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader {
    INSTANCE;

    private Semaphore semaphore = new Semaphore(3);

    public void download() {
        try {
            semaphore.acquire();
            downloadData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }


    }

    private void downloadData() throws InterruptedException {
        System.out.println("downloading data from the web");
        Thread.sleep(2000);
        System.out.println("-------------------------------");
    }
}

public class SemaphoresExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 120; i++) {
            executorService.execute(() -> {
                Downloader.INSTANCE.download();
            });
        }
    }
}
