package lesson11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Publisher {
    private final Queue<String> messageQueue = new LinkedList<>();
    private Subscriber subscriber;
    private boolean running = true;

    public void subscribe(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public synchronized void publish(String message) {
        messageQueue.add(message);
        notify();
    }

    public synchronized String getNextMessage(){
        while (messageQueue.isEmpty() && running){
            try{
                wait();
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        return messageQueue.poll();
    }

    public void stop(){
        running = false;
        synchronized (this) {
            notifyAll(); // разбудить поток подписчика для завершения
        }
    }
}

class Subscriber implements Runnable {
    private final Publisher publisher;
    public Subscriber(Publisher publisher) {
        this.publisher = publisher;
    }

    public void run() {
        while (true) {
            String message = publisher.getNextMessage();
            if (message == null) break; // очередь пуста и программа завершается
            if (message.equalsIgnoreCase("exit")) break;
            System.out.println("Подписчик получил: " + message);
        }
        System.out.println("Подписчик завершил работу.");
    }
}

public class PubSub {
    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        Subscriber subscriber = new Subscriber(publisher);
        publisher.subscribe(subscriber);

        Thread subscriberThread = new Thread(subscriber);
        subscriberThread.start();

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите слова (или 'exit' для выхода):");

        while (true) {
            String input = sc.nextLine();
            publisher.publish(input);
            if (input.equalsIgnoreCase("exit")) {
                publisher.stop();
                break;
            }
        }

        try {
            subscriberThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Программа завершена.");
    }
}