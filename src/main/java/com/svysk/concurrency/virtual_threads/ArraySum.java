package com.svysk.concurrency.virtual_threads;

import java.util.concurrent.atomic.AtomicInteger;

public class ArraySum implements Runnable {

    int[] arrayInt;

    String text;

    private AtomicInteger sum = new AtomicInteger(0);

    public ArraySum(String text, int[] arrayInt) {
        this.text = text;
        this.arrayInt = arrayInt;
    }

    @Override
    public void run() {
        System.out.println(this.text);
        for(int val: arrayInt) {
            sum.addAndGet(val);
        }
    }

    public int getSum() {
        return sum.get();
    }
}
