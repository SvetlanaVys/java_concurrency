package com.svysk.concurrency.concurrent_collections.concurrentNavigableMap.actions;

import com.svysk.concurrency.concurrent_collections.concurrentNavigableMap.EventCalendar;

import java.time.LocalDate;

public class RemoveEvent implements Runnable {

    private EventCalendar eventCalendar;

    private LocalDate eventDate;

    private String eventName;

    public RemoveEvent(EventCalendar eventCalendar, LocalDate eventDate, String eventName) {
        this.eventCalendar = eventCalendar;
        this.eventDate = eventDate;
        this.eventName = eventName;
    }

    @Override
    public void run() {
        eventCalendar.removeEvent(this.eventDate, this.eventName);
    }
}
