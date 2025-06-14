package dev.elshan.tim_buchalka.sec04.producer_consumer.challenge;

public class Main {
    public static void main(String[] args) {
        var shoeHouse = new ShoeWarehouse();
        var producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shoeHouse.receiveOrder();
            }
        });

        var consumer1 = new Thread(() -> {
            shoeHouse.fullFillOrder();
        });

        var consumer2 = new Thread(() -> {
            shoeHouse.fullFillOrder();
        });

        producer.start();
        consumer1.start();
        consumer2.start();
    }
}
