package dev.elshan.tim_buchalka.sec03;

public class ThreadStates {

    public static void main(String[] args) {
        System.out.println("Main thread running");
        try {
            System.out.println("Main thread paused for one second");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread secondThread = new Thread(() -> {
           var threadName = Thread.currentThread().getName();
            System.out.println(threadName + " should take 10 dot to complete");
            for (int i = 0; i < 10; i++) {
                System.out.print(". ");
                try {
                    Thread.sleep(500);
//                    System.out.println("A. state = " + Thread.currentThread().getState());
                } catch (InterruptedException e) {
                    System.out.println("\nWhoops " + threadName + " interrupted");
                    Thread.currentThread().interrupt();
//                    System.out.println("A1. state = " + Thread.currentThread().getState());
                    return;
                }
            }
            System.out.println("\n" + threadName + " completed");
        }, "Second thread");

        Thread thirdThread = new Thread(() -> {
            var threadName = Thread.currentThread().getName();
            for (int i = 0; i < 3; i++) {
                System.out.println(threadName + " installing package " + (i + 1));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Third thread");



        System.out.println("Main thread would continue here");

        var threadMonitor = new Thread(() -> {
            long now = System.currentTimeMillis();
            while (secondThread.isAlive()){
                System.out.println("\nWaiting second thread to complete");
                try {
                    Thread.sleep(1000);
//                System.out.println("B. state = " + secondThread.getState());
                    if(System.currentTimeMillis() - now > 2000){
                        secondThread.interrupt();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("Second thread is starting");
        secondThread.start();
        threadMonitor.start();


        try {
            secondThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(!secondThread.isInterrupted()){
            System.out.println("Third thread is starting");
            thirdThread.start();
        }else{
            System.out.println("Previous thread is interrupted, " + secondThread.getName() + " can't run");
        }


//        System.out.println("C. state = " + secondThread.getState());
    }
}
