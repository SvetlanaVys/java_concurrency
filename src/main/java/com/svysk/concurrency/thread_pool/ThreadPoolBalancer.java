package com.svysk.concurrency.thread_pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.lang.management.ManagementFactory;

public class ThreadPoolBalancer {

    Task[] tasks;

    int threadNumbers = 10;

    double loadCapacity = 2.0;

    public ThreadPoolBalancer(Task[] tasks) {
        this.tasks = tasks;
    }

    public ThreadPoolBalancer(Task[] tasks, double loadCapacity) {
        this.tasks = tasks;
        this.loadCapacity = loadCapacity;
    }


    public void runTasks() {
        System.out.println("Create Thread Pool");
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadNumbers);
        threadPool.setMaximumPoolSize(tasks.length / 2);
        for (Task task : tasks) {
            threadPool.execute(task);

            double systemLoadAverage = ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage();

            if (systemLoadAverage < loadCapacity || threadPool.getActiveCount() >= this.threadNumbers) {
                this.threadNumbers += Math.round(this.threadNumbers * ((Math.random() * (4 - 1.5)) + 1.5));
                threadPool.setCorePoolSize(this.threadNumbers > threadPool.getMaximumPoolSize() ?
                        threadPool.getMaximumPoolSize() : this.threadNumbers);
                System.out.println("-------------------------------- Increase corePoolSize to " + systemLoadAverage);
                System.out.println("-------------------------------- systemLoadAverage " + systemLoadAverage);
            } else {
                this.threadNumbers -= (int) Math.round(this.threadNumbers / ((Math.random() * (4 - 1.5)) + 1.5));
                threadPool.setCorePoolSize(this.threadNumbers > threadPool.getMaximumPoolSize() ?
                        threadPool.getMaximumPoolSize() : this.threadNumbers);
                System.out.println("--------------------------------------------- systemLoadAverage " + systemLoadAverage);
            }
            System.out.println("Currently executing threads: " + threadPool.getActiveCount());
        }
    }

    // ? find amount of cores (hardware characteristic)
    // int cores = Runtime.getRuntime().availableProcessors();


    public void setThreadNumbers(int threadNumbers) {
        this.threadNumbers = threadNumbers;
    }
}
