package com.svysk.concurrency;

import java.util.concurrent.Callable;

public class WaitingThreadState implements Callable<Thread.State> {

    public Thread.State call() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return WaitingThread.t1.getState();
    }
}