package dev.elshan.tim_buchalka.sec05.locks.challengeWithLocks;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MessageRepository {

    private String message;
    private boolean hasMessage = false;
    private Lock lock = new ReentrantLock();

    public String read() {
        if (lock.tryLock()) {
            try {
                while (!hasMessage) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                hasMessage = false;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("** read blocked");
            hasMessage = false;
        }
        return message;
    }

    public void write(String message) {
        if (lock.tryLock()) {
            try {
                while (hasMessage) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                hasMessage = true;
            }finally {
                lock.unlock();
            }
        }else{
            System.out.println("** Write blocked");
            hasMessage = true;
        }
        this.message = message;
    }
}

class MessageWriter implements Runnable {

    private MessageRepository outgoingMessage;

    private final String text = """
            Humpty Dumpty sat on a wall,
            Humpty Dumpty had a great fall,
            All the king's horses and all the king's men,
            Couldn't put Humpty together again.""";

    public MessageWriter(MessageRepository outgoingMessage) {
        this.outgoingMessage = outgoingMessage;
    }

    @Override
    public void run() {

        Random random = new Random();
        String[] lines = text.split("\n");

        for (int i = 0; i < lines.length; i++) {
            outgoingMessage.write(lines[i]);
            try {
                Thread.sleep(random.nextInt(500, 2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        outgoingMessage.write("Finished");
    }
}

class MessageReader implements Runnable {

    private MessageRepository incomingMessage;

    public MessageReader(MessageRepository incomingMessage) {
        this.incomingMessage = incomingMessage;
    }

    @Override
    public void run() {

        Random random = new Random();
        String latestMessage = "";

        do {
            try {
                Thread.sleep(random.nextInt(500, 2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            latestMessage = incomingMessage.read();
            System.out.println(latestMessage);
        } while (!latestMessage.equals("Finished"));
    }
}

public class ProducerConsumer {

    public static void main(String[] args) {

        MessageRepository messageRepository = new MessageRepository();

        Thread reader = new Thread(new MessageReader(messageRepository));
        Thread writer = new Thread(new MessageWriter(messageRepository));

        reader.setUncaughtExceptionHandler((thread, err) -> {
            System.out.println("Reader has an exception " + err);
            if (writer.isAlive()) {
                writer.interrupt();
            }
        });

        writer.setUncaughtExceptionHandler((thread, err) -> {
            System.out.println("Writer has an exception " + err);
            if (reader.isAlive()) {
                reader.interrupt();
            }
        });

        reader.start();
        writer.start();
    }
}
