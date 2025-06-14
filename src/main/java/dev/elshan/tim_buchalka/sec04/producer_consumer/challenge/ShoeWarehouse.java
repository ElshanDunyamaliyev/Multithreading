package dev.elshan.tim_buchalka.sec04.producer_consumer.challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShoeWarehouse {
    public List<String> products = new ArrayList<>(List.of("product1","product2","product3"));
    private List<Order> orders = new ArrayList<>(10);
    private Random random = new Random();

    public synchronized void receiveOrder(){
        int i = random.nextInt(0, 2);
        while (orders.size() == 10){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        var order = new Order(random.nextInt(1,100000), products.get(i), random.nextInt(3,10));
        orders.add(order);
        System.out.println("Adding order " + order.getId());
        notifyAll();
    }

    public synchronized void fullFillOrder(){
        while (orders.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for (int j = 0; j < 5; j++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            orders.removeFirst();
            System.out.println("Removing order " + orders.getFirst().getId());
            notifyAll();
        };
    }

}
