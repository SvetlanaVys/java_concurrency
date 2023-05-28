package com.svysk.concurrency.concurrent_collections.concurrentNavigableMap;

import com.svysk.concurrency.concurrent_collections.concurrentNavigableMap.actions.AddEvent;
import com.svysk.concurrency.concurrent_collections.concurrentNavigableMap.actions.DisplayEvents;
import com.svysk.concurrency.concurrent_collections.concurrentNavigableMap.actions.RemoveEvent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventCalendarMain {

    public static void main( String[] args ) {

        EventCalendar eventCalendar = new EventCalendar();
        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(
                new AddEvent(eventCalendar, LocalDate.of(2022, 5, 20), "Event 1"))
        );
        threads.add(new Thread(
                new AddEvent(eventCalendar, LocalDate.of(2022, 10, 8), "Event 2"))
        );
        threads.add(new Thread(
                new AddEvent(eventCalendar, LocalDate.of(2023, 5, 11), "Event 3"))
        );
        threads.add(new Thread(
                new AddEvent(eventCalendar, LocalDate.of(2022, 10, 8), "Event 4"))
        );
        threads.add(new Thread(
                new AddEvent(eventCalendar, LocalDate.of(2022, 5, 20), "Event 5"))
        );
        threads.add(new Thread(
                new RemoveEvent(eventCalendar, LocalDate.of(2022, 10, 8), "Event 4"))
        );
        threads.add(new Thread(
                new DisplayEvents(eventCalendar, LocalDate.of(2022, 10, 8)))
        );

        for(Thread thread : threads) {
            thread.start();
        }

    }
}
