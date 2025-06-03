package dev.elshan.tim_buchalka.sec02;

import java.util.concurrent.TimeUnit;

public class ImplementingRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Runnable thread is running: seconds " + (i + 1));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
