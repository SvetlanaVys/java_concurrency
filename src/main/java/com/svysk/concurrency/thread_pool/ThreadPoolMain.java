package com.svysk.concurrency.thread_pool;

import java.util.Scanner;

public class ThreadPoolMain {

    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of tasks: ");
        int numbeOfTasks = scanner.nextInt();
        System.out.println("Enter task duration: ");
        int taskDuration = scanner.nextInt();

        Task[] tasks = new Task[numbeOfTasks];

        for(int i = 0; i < numbeOfTasks; i++) {
            tasks[i] = new Task(i, taskDuration);
        }

        ThreadPoolBalancer balancer = new ThreadPoolBalancer(tasks);
        balancer.runTasks();
    }
}
