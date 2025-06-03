package dev.elshan.tim_buchalka.sec02;

import java.time.Duration;

public class ExtendingThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Custom thread is running, seconds " + (i + 1));
            try {
                Thread.sleep(Duration.ofSeconds(1));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
