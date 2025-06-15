package dev.elshan.tim_buchalka.sec06;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        var random = new Random();
        var carFactory = new CarFactory();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService cachedThreadPool2 = Executors.newCachedThreadPool();
        Runnable runnable = () -> {
            var id = random.nextInt(1, 10000);
            var order = new Order(id, CarFactory.products.get(random.nextInt(0, 5)), random.nextInt(1, 4));
            carFactory.receiveOrder(order);
        };

        for (int i = 0; i < 10; i++) {
            cachedThreadPool.execute(runnable);
        }

        for (int i = 0; i < 5; i++) {
            cachedThreadPool2.execute(() -> carFactory.fullFillOrder());
        }

    }
}
