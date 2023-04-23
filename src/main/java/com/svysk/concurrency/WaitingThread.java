package com.svysk.concurrency;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import static java.lang.Thread.*;

public class WaitingThread implements Runnable {
    public static Thread t1;

    public void run() {
        FutureTask<State> task = new FutureTask<>(new WaitingThreadState());
        try(ExecutorService pool = Executors.newSingleThreadExecutor()) {
            pool.submit(task);
            task.get();
        } catch (ExecutionException|InterruptedException e) {
            currentThread().interrupt();
        }
    }
}
