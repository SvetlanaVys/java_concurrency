package com.svysk.concurrency.concurrent_collections.blockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PostOfficeMain {

    public static void main(String[] args) {

        BlockingQueue<Mail> mailQueue = new LinkedBlockingQueue<>();

        new Thread(new Producer(mailQueue, "Sender 1", "Content 1")).start();
        new Thread(new Producer(mailQueue, "Sender 2", "Content 2")).start();
        new Thread(new Consumer(mailQueue)).start();
        new Thread(new Consumer(mailQueue)).start();
        new Thread(new Producer(mailQueue, "Sender 3", "Content 3")).start();
        new Thread(new Producer(mailQueue, "Sender 4", "Content 4")).start();
        new Thread(new Producer(mailQueue, "Sender 5", "Content 5")).start();
        new Thread(new Consumer(mailQueue)).start();
        new Thread(new Consumer(mailQueue)).start();
        new Thread(new Consumer(mailQueue)).start();
        new Thread(new Consumer(mailQueue)).start();
        new Thread(new Consumer(mailQueue)).start();
    }
}
