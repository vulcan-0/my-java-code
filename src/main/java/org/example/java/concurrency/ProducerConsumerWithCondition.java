package org.example.java.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithCondition {

    private final Queue<Integer> queue = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public void produce() throws InterruptedException {
        lock.lock();
        try {
            int capacity = 10;
            while (queue.size() == capacity) {
                notFull.await();
            }
            queue.add(1);
            System.out.println(Thread.currentThread().getName() + " Produced, queue size: " + queue.size());
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            queue.poll();
            System.out.println(Thread.currentThread().getName() + " Consumed, queue size: " + queue.size());
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }

}
