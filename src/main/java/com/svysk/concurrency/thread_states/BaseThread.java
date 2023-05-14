package com.svysk.concurrency.thread_states;

public class BaseThread implements Runnable {

    String threadName;

    Object lock;

    public BaseThread() {
        this.threadName = "Thread 0";
        this.lock = new Object();
    }

    public BaseThread(String threadName, Object lock) {
        this.threadName = threadName;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.threadName + " is running...");
            synchronized (this.lock) {
                System.out.println("Synchronized block");
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            System.out.println(this.threadName + "'s InterruptedException: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
