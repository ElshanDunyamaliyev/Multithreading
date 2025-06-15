package dev.elshan.tim_buchalka.sec06;

import java.util.List;
import java.util.concurrent.*;

public class ExecutorService1 {
    public static void main(String[] args) {
        int sum = sum(0, 10, 2);
        System.out.println(sum);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        List<Callable<Integer>> callables = List.of(() -> sum(0, 10, 2),() -> sum(0, 10, 2),() -> sum(0, 10, 2));
        try {
            List<Future<Integer>> futures = cachedThreadPool.invokeAll(callables);
            System.out.println(futures);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            cachedThreadPool.shutdown();
        }

//        try {
//            Future<Integer> submit1 = cachedThreadPool.submit(() -> sum(0, 10, 2));
//            Future<Integer> submit2 = cachedThreadPool.submit(() -> sum(0, 20, 2));
//            Future<Integer> submit3 = cachedThreadPool.submit(() -> sum(0, 30, 2));
//
//            try {
//                System.out.println(submit1.get(500, TimeUnit.MILLISECONDS));
//                System.out.println(submit2.get(500, TimeUnit.MILLISECONDS));
//                System.out.println(submit3.get(500, TimeUnit.MILLISECONDS));
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//
//        } finally {
//            cachedThreadPool.shutdown();
//        }


    }

    private static int sum(int start, int end, int delta) {

        int sum = 0;
        for (int i = start; i <= end; i += delta) {
            sum += i;
        }
        System.out.println(Thread.currentThread().getName() + ", "
                + "  " + sum);
        return sum;
    }


    // Fixed Thread Pool
    public static void main3(String[] args) throws InterruptedException {
        String[] files = {"file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt", "file6.txt", "file7.txt", "file8.txt", "file9.txt", "file10.txt"};

        // Create a cached thread pool
        // Will create 10 threads
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        for (String file : files) {
            Runnable task = () -> {
                System.out.println(Thread.currentThread().getName() + " started processing " + file);
                try {
                    // Simulate time-consuming file processing (e.g., reading, parsing)
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Good practice
                    System.err.println("Task interrupted: " + file);
                }
                System.out.println(Thread.currentThread().getName() + " finished processing " + file);
            };

            cachedThreadPool.execute(task);
        }
        Thread.sleep(6000);

        // After completing 10 task will use 4 thread to complete task in below
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            Runnable task = () -> {
                System.out.println(Thread.currentThread().getName() + " massively started processing " + finalI);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Good practice
                    System.err.println("Task interrupted: " + finalI);
                }
                System.out.println(Thread.currentThread().getName() + " massively finished processing " + finalI);
            };
            cachedThreadPool.execute(task);
        }


        cachedThreadPool.shutdown();
        cachedThreadPool.awaitTermination(10, TimeUnit.SECONDS);


    }

    // Fixed Thread Pool
    public static void main2(String[] args) {
        String[] files = {"file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt", "file6.txt"};

        // Create a thread pool with 3 threads
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        for (String file : files) {
            Runnable task = () -> {
                System.out.println(Thread.currentThread().getName() + " started processing " + file);
                try {
                    // Simulate time-consuming file processing (e.g., reading, parsing)
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Good practice
                    System.err.println("Task interrupted: " + file);
                }
                System.out.println(Thread.currentThread().getName() + " finished processing " + file);
            };

            fixedThreadPool.execute(task);
        }

        fixedThreadPool.shutdown();
    }

    public static void main1(String[] args) {
        ExecutorService thread1 = Executors.newSingleThreadExecutor();
        ExecutorService thread2 = Executors.newSingleThreadExecutor();
        ExecutorService thread3 = Executors.newSingleThreadExecutor();

        thread1.execute(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        });

        thread1.shutdown();

        boolean isDone;
        try {
            isDone = thread1.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (isDone) {
            System.out.println("Thread 1 is completed");
            thread2.execute(() -> {
                for (int i = 5; i < 10; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
            });

            thread2.shutdown();

            thread3.execute(() -> {
                for (int i = 10; i < 15; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
            });

            thread3.shutdown();
        }


    }
}
