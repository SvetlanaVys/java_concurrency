package com.svysk.concurrency.concurrent_collections.concurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

public class WordsCounter implements Runnable {

    static ConcurrentHashMap<String, Integer> words = new ConcurrentHashMap();
    private String textPart;

    public WordsCounter(String textPart) {
        this.textPart = textPart.toLowerCase().replaceAll("[\\.\\,]", "");
    }

    @Override
    public void run() {
        for (String str : textPart.split("[ \\t]+")) {
            if (words.get(str) != null) {
                words.put(str, words.get(str) + 1);
            } else {
                words.put(str, 1);
            }
        }
    }

    public void displayWords() {
        words.forEach((k, v) -> System.out.printf("    word: %s, count: %s%n", k, v));
    }
}
