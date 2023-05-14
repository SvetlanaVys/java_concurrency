package com.svysk.concurrency;

import com.svysk.concurrency.thread_states.BaseThread;
import com.svysk.concurrency.thread_states.WaitingThread;
import com.svysk.concurrency.thread_states.WaitingThreadState;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreadStatesTest {

    @Test
    void threadNewState() {
        Thread thread = new Thread(new BaseThread());
        assertEquals(Thread.State.NEW, thread.getState());
    }

    @Test
    void threadRunnableState() {
        Thread thread = new Thread(new BaseThread());
        thread.start();
        assertEquals(Thread.State.RUNNABLE, thread.getState());
    }

    @Test
    void threadBlockedState() throws InterruptedException {
        Object lock = new Object();
        Thread t1 = new Thread(new BaseThread("Thread 1", lock));
        Thread t2 = new Thread(new BaseThread("Thread 2", lock));

        t1.start();
        Thread.sleep(1000);
        t2.start();

        Thread.sleep(1000);
        assertEquals(Thread.State.BLOCKED, t2.getState());
    }

    @Test
    void threadWaitingState() throws InterruptedException, ExecutionException {
        WaitingThread.t1 = new Thread(new WaitingThread());
        WaitingThread.t1.start();
        WaitingThreadState task = new WaitingThreadState();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Thread.State> future = executorService.submit(task);

        assertEquals(Thread.State.WAITING, future.get());
    }

    @Test
    void threadTimedWaitingState() throws InterruptedException {
        Thread thread = new Thread(new BaseThread());
        thread.start();
        Thread.sleep(1000);
        assertEquals(Thread.State.TIMED_WAITING, thread.getState());
    }

    @Test
    void threadTerminatedState() throws InterruptedException {
        Thread thread = new Thread(new BaseThread());
        thread.start();
        thread.join();
        assertEquals(Thread.State.TERMINATED, thread.getState());
    }
}
