package dev.elshan.tim_buchalka.sec04.synchronization;

public class Client {
    public static void main(String[] args) {
        var bankApplication = new BankApp(10000);

        var thread1 = new Thread(() -> bankApplication.withdraw(2500));
        var thread2 = new Thread(() -> bankApplication.deposit(5000));
        var thread3 = new Thread(() -> bankApplication.withdraw(2500));

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(bankApplication.getBalance());
    }
}
