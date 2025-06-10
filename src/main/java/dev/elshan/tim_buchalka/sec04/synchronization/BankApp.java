package dev.elshan.tim_buchalka.sec04.synchronization;

import java.util.concurrent.TimeUnit;

public class BankApp {
    private double balance;
    private final Object monitorObject = new Object();

    public BankApp(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (monitorObject) {
            balance += amount;
            addPromo(amount);
        }
    }

    // this will get lock, because thread already has acquired lock from deposit method
    private void addPromo(double amount) {
        if (amount >= 5000) {
            synchronized (monitorObject) {
                this.balance += 25;
            }
        }
    }

    public void withdraw(double amount) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (monitorObject) {
            if (amount > balance) {
                System.out.println("Insufficient amount");
            } else {
                balance -= amount;
            }
        }
    }



//    public synchronized void deposit(double amount) {
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        balance += amount;
//    }
//
//    public synchronized void withdraw(double amount) {
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        if (amount > balance) {
//            System.out.println("Insufficient amount");
//        } else {
//            balance -= amount;
//        }
//    }
}
