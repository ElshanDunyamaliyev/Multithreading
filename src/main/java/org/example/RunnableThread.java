package org.example;

public class RunnableThread implements Runnable{
    @Override
    public void run() {
        System.out.println("Second way of creating thread is implementing runnable and pass it to Thread constructor");
    }
}
