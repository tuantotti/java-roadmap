package com.java.concurrency.callable_future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Processor implements Callable<String> {
    private String id;

    public Processor(String id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        return "Id: " + id;
    }
}

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Future<String> future = executorService.submit(new Processor(String.valueOf(i + 1)));
            futures.add(future);
        }

        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

}
