package dev.elshan.tim_buchalka.sec05.challengeWithWaitAndNotifyAndSynchronized;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        var random = new Random();
        var carFactory = new CarFactory();
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                var id = random.nextInt(1, 10000);
                var order = new Order(id, CarFactory.products.get(random.nextInt(0, 5)), random.nextInt(1, 4));
                carFactory.receiveOrder(order);
            }
        }, "producer");

        Thread consumer1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                carFactory.fullFillOrder();
            }
        }, "consumer1");

        Thread consumer2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                carFactory.fullFillOrder();
            }
        }, "consumer2");

        producer.start();
        consumer1.start();
        consumer2.start();

        try {
            producer.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
