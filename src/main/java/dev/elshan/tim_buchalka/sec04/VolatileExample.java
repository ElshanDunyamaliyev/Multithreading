package dev.elshan.tim_buchalka.sec04;

import java.util.concurrent.TimeUnit;

public class VolatileExample {
    // Burda her threadin kicik ve suretli bir yaddas olurki burda ortaq variable deyerini saxlayir
    // yeni bir threadde ortaq bir valuenu deyissek basqa threadler onu derhal gormeyecek
    // bunun ucun volatile keywordunden istifade etmeliyik
    // bu zaman gedib heapden oxuyacaqlar

    public volatile boolean flag = false;

    public void toggleFlag(){
        flag = !flag;
    }

    public boolean getFlag(){
        return flag;
    }

    public static void main(String[] args) {

        var volatileExample = new VolatileExample();

        var writerThread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            volatileExample.toggleFlag();
            System.out.println("A. flag set to " + volatileExample.getFlag());
        });

        var readerThread = new Thread(() -> {
            while (!volatileExample.getFlag()){
                // wait until flag is ready
            }
            System.out.println("B. Flag is " + volatileExample.getFlag());
        });

        writerThread.start();
        readerThread.start();


    }
}
