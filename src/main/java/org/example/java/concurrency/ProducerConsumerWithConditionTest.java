package org.example.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("DuplicatedCode")
public class ProducerConsumerWithConditionTest {

    public static void main(String[] args) {
        ProducerConsumerWithCondition producerConsumerWithCondition = new ProducerConsumerWithCondition();

        ExecutorService executor = Executors.newCachedThreadPool();
        batchExecute(executor, () -> {
            try {
                producerConsumerWithCondition.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        batchExecute(executor, () -> {
            try {
                producerConsumerWithCondition.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void batchExecute(ExecutorService executor, Runnable runnable) {
        for (int i = 0; i < 100; i++) {
            executor.execute(runnable);
        }
    }

}
