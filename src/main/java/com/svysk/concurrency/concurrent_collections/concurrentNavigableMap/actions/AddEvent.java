package com.svysk.concurrency.concurrent_collections.concurrentNavigableMap.actions;

import com.svysk.concurrency.concurrent_collections.concurrentNavigableMap.EventCalendar;

import java.time.LocalDate;

public class AddEvent implements Runnable {

    private EventCalendar eventCalendar;

    private LocalDate eventDate;

    private String eventName;

    public AddEvent(EventCalendar eventCalendar, LocalDate eventDate, String eventName) {
        this.eventCalendar = eventCalendar;
        this.eventDate = eventDate;
        this.eventName = eventName;
    }

    @Override
    public void run() {
        eventCalendar.addEvent(this.eventDate, this.eventName);
    }
}
