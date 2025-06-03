package dev.elshan.tim_buchalka.sec02;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        var thread1 = new ExtendingThread();
        var thread2 = new Thread(new ImplementingRunnable());
        // Difference between start and run: start is asynchronous and run is synchronous
        thread1.start();
        thread2.start();
//        thread1.run();
        for (int i = 0; i < 10; i++) {
            System.out.println("Main thread is running, second: " + (i + 1));
            try {
                Thread.sleep(Duration.ofMillis(500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
