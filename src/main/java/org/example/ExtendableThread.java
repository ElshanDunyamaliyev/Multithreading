package org.example;

public class ExtendableThread extends Thread{

    @Override
    public void run() {
        System.out.println("First way of creating thread is extending Thread class");
        for (int i = 0; i < 5; i++) {
            System.out.println(1);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
