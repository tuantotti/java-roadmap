package com.java.concurrency.wait_and_notify;

public class Main {
    public static void main(String[] args) {
        Process process = new Process();
        Thread thread = new Thread(() -> {
            try {
                process.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread1 = new Thread(() -> {
            try {
                process.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        thread.start();
        thread1.start();
    }
}

class Process {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Running the produce method...");
            wait();
            System.out.println("Run the produce again");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(5000);

        synchronized (this) {
            System.out.println("Consume method is executed...");
            notify();
            System.out.println("After notify");
        }
    }
}