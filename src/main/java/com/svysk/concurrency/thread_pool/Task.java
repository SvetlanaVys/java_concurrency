package com.svysk.concurrency.thread_pool;

public class Task implements Runnable {
    private final int taskNumber;

    private final int duration;

    public Task(int taskNumber, int duration) {
        this.taskNumber = taskNumber;
        this.duration = duration;
    }

    @Override
    public void run() {
        try {
            System.out.println("Task " + taskNumber + " is running...");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
