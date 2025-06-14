package dev.elshan.tim_buchalka.sec05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lecture2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 9; i++) {
            int finalI = i;
            executorService.execute(() -> {
                System.out.println("Executing task "+ (finalI + 1));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executorService.shutdown();
    }
}
