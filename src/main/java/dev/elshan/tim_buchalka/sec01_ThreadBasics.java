package dev.elshan.tim_buchalka;

public class sec01_ThreadBasics {
    public static void main(String[] args) {
        var thread = Thread.currentThread();
        System.out.println(thread.getState());
        System.out.println(thread.getThreadGroup());
        System.out.println(thread.getName());
        System.out.println(thread.isAlive());
        System.out.println(thread.isDaemon());
        System.out.println(thread.isVirtual());
        System.out.println(thread.getPriority());
        System.out.println(thread);

        System.out.println("-----------------------");
        thread.setName("My main thread");
        thread.setPriority(Thread.MAX_PRIORITY);
        System.out.println(thread.getName());
        System.out.println(thread.getPriority());
//        thread.yield();
    }
}
