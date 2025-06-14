package dev.elshan.tim_buchalka.sec04.test;

import java.util.Random;

public class MessageRepository {

    boolean hasMessage = false;
    String content = "";

    public synchronized void write(String content) {

        while (hasMessage) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        hasMessage = true;
        notifyAll();
        this.content = content;
    }

    public synchronized String read() {
        while (!hasMessage) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        hasMessage = false;
        notifyAll();
        return content;
    }
}

class MessageWriter implements  Runnable{
    private MessageRepository messageRepository;

    public MessageWriter(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void run() {
        var text = """
                Up branch to easily missed by do.
                Admiration considered acceptance too led one melancholy expression.
                Are will took form the nor true. Winding enjoyed minuter her letters evident use eat colonel.
                He attacks observe mr cottage inquiry am examine gravity. Are dear but near left was.
                Year kept on over so as this of.
                She steepest doubtful betrayed formerly him.
                Active one called uneasy our seeing see cousin tastes its.
                Ye am it formed indeed agreed relied piqued.
                """;

        var random = new Random();
        String[] split = text.split("\n");
        for (int i = 0; i < split.length; i++) {
            messageRepository.write(split[i]);
            System.out.println("Writing line: " + (i + 1));
            try {
                Thread.sleep(random.nextInt(500, 2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        messageRepository.write("Finished");
    }
}


class MessageReader implements  Runnable{
    private MessageRepository messageRepository;


    public MessageReader(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void run() {
        var random = new Random();
        do {
            String content = messageRepository.read();
            System.out.println(content);
            try {
                Thread.sleep(random.nextInt(500, 2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }while (!messageRepository.read().equalsIgnoreCase("finished"));
    }
}

class Main{
    public static void main(String[] args) {
        var msgRepo = new MessageRepository();
        var producerThread = new Thread(new MessageWriter(msgRepo));
        var consumerThread = new Thread(new MessageReader(msgRepo));
        producerThread.start();
        consumerThread.start();
    }
}