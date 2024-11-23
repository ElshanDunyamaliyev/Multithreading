package org.example;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
//        Thread thread = Thread.currentThread();
//        printStateInformation(thread);

        // With lambda expression we are overriding run method in runnable interface
//        Thread newThread = new Thread(() -> {
//            System.out.println("New thread started");
//            throw new RuntimeException("Something happened");
//        });

        // With setUncaughtExceptionHandler if error happens we can clean up resources or log additional info
//        newThread.setUncaughtExceptionHandler((Thread t,Throwable y) -> {
//            System.out.println(t.getName() + "\n" + y.getMessage());
//        });
//        newThread.start();

        Thread extendableThread = new ExtendableThread();
        extendableThread.start();

        for (int i = 0; i < 3; i++) {
            System.out.println(0);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

//        Thread runnableThread = new Thread(new RunnableThread());
//        runnableThread.start();
    }

    public static void printStateInformation(Thread thread){
        System.out.println(thread.getId());
        thread.setName("I am Main Thread");
        System.out.println(thread.getName());
        System.out.println(thread.getPriority());
        System.out.println(thread.getState());
        System.out.println(thread.getThreadGroup());
        System.out.println(thread.isAlive());
        System.out.println(thread.isInterrupted());
        System.out.println(thread.isDaemon());
    }
}