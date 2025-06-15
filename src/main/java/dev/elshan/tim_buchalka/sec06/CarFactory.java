package dev.elshan.tim_buchalka.sec06;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CarFactory {
    public static List<String> products = new ArrayList<>(List.of("ferrari","bugatti","mercedes","lamborghini","nissan","tesla"));
    private List<Order> orders = new ArrayList<>();

    public synchronized void receiveOrder(Order order){
        while (orders.size() >= 10){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Adding order with id: " + order.getId());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        orders.add(order);
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
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Order removed = orders.removeFirst();
        System.out.println(Thread.currentThread().getName() + " removing order with id: " + removed.getId());
        notifyAll();
    }

}
