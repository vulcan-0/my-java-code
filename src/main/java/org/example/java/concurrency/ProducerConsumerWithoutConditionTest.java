package org.example.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("DuplicatedCode")
public class ProducerConsumerWithoutConditionTest {

    public static void main(String[] args) {
        ProducerConsumerWithoutCondition producerConsumerWithoutCondition = new ProducerConsumerWithoutCondition();

        ExecutorService executor = Executors.newCachedThreadPool();
        batchExecute(executor, () -> {
            try {
                producerConsumerWithoutCondition.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        batchExecute(executor, () -> {
            try {
                producerConsumerWithoutCondition.consume();
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
