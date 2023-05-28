package com.svysk.concurrency.virtual_threads;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

public class VMMain {

    public static void main( String[] args ) throws InterruptedException, NoSuchAlgorithmException {

        Random rand = SecureRandom.getInstanceStrong();

        int[] arrayInt = arrayInit(100, rand);
        int segmentCount = 5;
        int n = 0, m = arrayInt.length / segmentCount, i = 0, result = 0;

        ArraySum[] arraySums = new ArraySum[segmentCount];
        Thread[] thArr = new Thread[segmentCount];

        while(n < m) {
            arraySums[i] = new ArraySum("Sum array part: [" + n + ":" + m + "]", Arrays.copyOfRange(arrayInt, n, m + 1));
            thArr[i] = virtualThread("Sum array part: [" + n + ":" + m + "]", arraySums[i]);

            n = m + 1;
            m += arrayInt.length / segmentCount;
            m = m > arrayInt.length ? arrayInt.length : m;
            i++;
        }

        for(int j = 0; j < thArr.length; j++) {
            thArr[j].join();
            result += arraySums[j].getSum();
        }

        System.out.println("Sum: " + result);
    }

    private static int[] arrayInit(int n, Random rand) {
        int[] arrayInt = new int[n];
        int testSum = 0;
        for(int i = 0; i < n; i++) {
            arrayInt[i] = rand.nextInt(50);
            System.out.print(arrayInt[i] + " ");
            testSum += arrayInt[i];
        }

        System.out.println();
        System.out.println("Test Sum: " + testSum);
        System.out.println();
        return arrayInt;
    }

    private static Thread virtualThread(String name, Runnable runnable) {
        return Thread.ofVirtual()
                .name(name)
                .start(runnable);
    }
}
