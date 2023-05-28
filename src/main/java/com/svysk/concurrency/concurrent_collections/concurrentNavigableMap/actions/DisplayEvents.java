package com.svysk.concurrency.concurrent_collections.concurrentNavigableMap.actions;

import com.svysk.concurrency.concurrent_collections.concurrentNavigableMap.EventCalendar;

import java.time.LocalDate;

public class DisplayEvents implements Runnable {

    private EventCalendar eventCalendar;

    private LocalDate eventDate;

    public DisplayEvents(EventCalendar eventCalendar, LocalDate eventDate) {
        this.eventCalendar = eventCalendar;
        this.eventDate = eventDate;
    }

    public DisplayEvents(EventCalendar eventCalendar) {
        this.eventCalendar = eventCalendar;
    }

    @Override
    public void run() {
        try {
            while (eventCalendar.getEvents(this.eventDate) == null || eventCalendar.getEvents(this.eventDate).isEmpty()) {
                Thread.sleep(10);
            }
            System.out.println(eventCalendar.getEvents(this.eventDate));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
