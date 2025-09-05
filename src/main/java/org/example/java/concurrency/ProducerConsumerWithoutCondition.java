package org.example.java.concurrency;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerWithoutCondition {

    private final Queue<Integer> queue = new LinkedList<>();
    private final Object lock = new Object();

    public void produce() throws InterruptedException {
        synchronized (lock) {
            int capacity = 10;
            while (queue.size() == capacity) {
                lock.wait();
            }
            queue.add(1);
            System.out.println(Thread.currentThread().getName() + " Produced, queue size: " + queue.size());
            lock.notifyAll();
        }
    }

    public void consume() throws InterruptedException {
        synchronized (lock) {
            while (queue.isEmpty()) {
                lock.wait();
            }
            queue.poll();
            System.out.println(Thread.currentThread().getName() + " Consumed, queue size: " + queue.size());
            lock.notifyAll();
        }
    }

}
