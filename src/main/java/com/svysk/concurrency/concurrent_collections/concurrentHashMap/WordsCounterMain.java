package com.svysk.concurrency.concurrent_collections.concurrentHashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordsCounterMain {
    public static void main( String[] args ) {
        File myObj = new File("src/main/resources/sample.txt");
        try (Scanner myReader = new Scanner(myObj)) {

            int i = 0;
            List<Thread> threads = new ArrayList<>();
            List<WordsCounter> wordsCounter = new ArrayList<>();

            while (myReader.hasNextLine()) {
                wordsCounter.add(new WordsCounter(myReader.nextLine()));
                threads.add(new Thread(wordsCounter.get(i)));
                threads.get(i).start();
                i++;
            }

            for(Thread th : threads) {
                th.join();
            }

            wordsCounter.get(0).displayWords();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
