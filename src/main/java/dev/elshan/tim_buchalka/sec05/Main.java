package dev.elshan.tim_buchalka.sec05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            for (int i = 5; i > 0; i--) {
//                try {
////                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                System.out.println("Counting down: " + i);
            }
            System.out.println("Completed: " + Thread.currentThread().getName());
        });
        executorService.shutdown();
        boolean isDone;
        try {
            isDone = executorService.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(isDone){
            ExecutorService executorService2 = Executors.newSingleThreadExecutor();
            executorService2.execute(() -> {
                for (int i = 5; i > 0; i--) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Counting down: " + i);
                }
                System.out.println("Completed: " + Thread.currentThread().getName());
            });
            executorService2.shutdown();
        }

    }
}
