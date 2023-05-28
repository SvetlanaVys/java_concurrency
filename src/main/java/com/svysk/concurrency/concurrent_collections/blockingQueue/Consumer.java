package com.svysk.concurrency.concurrent_collections.blockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
    private final BlockingQueue<Mail> mailQueue;

    public Consumer(BlockingQueue mailQueue) {
        this.mailQueue = mailQueue;
    }

    @Override
    public void run() {
        try {
            Mail mail = this.mailQueue.poll(100, TimeUnit.MILLISECONDS);
            System.out.println(mail);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
