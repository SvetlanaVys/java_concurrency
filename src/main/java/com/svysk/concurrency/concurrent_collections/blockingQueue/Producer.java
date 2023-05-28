package com.svysk.concurrency.concurrent_collections.blockingQueue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private final BlockingQueue<Mail> mailQueue;
    private final Mail mail;

    public Producer(BlockingQueue mailQueue, String sender, String content) {
        this.mail = new Mail(sender, content);
        this.mailQueue = mailQueue;
    }

    @Override
    public void run() {
        this.mailQueue.offer(mail);
    }
}
