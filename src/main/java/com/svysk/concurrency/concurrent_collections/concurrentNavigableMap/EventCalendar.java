package com.svysk.concurrency.concurrent_collections.concurrentNavigableMap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class EventCalendar {

    static ConcurrentNavigableMap<LocalDate, List<String>> eventList = new ConcurrentSkipListMap<>();

    public void addEvent(LocalDate eventDate, String eventName) {
        System.out.println("Add an event: " + eventName + " to a date: " + eventDate);
        getList(eventDate).add(eventName);
    }

    public void removeEvent(LocalDate eventDate, String eventName) {
        System.out.println("Remove an event: " + eventName + " to a date: " + eventDate);
        getList(eventDate).remove(eventName);
    }

    public List<String> getEvents(LocalDate eventDate) {
        return eventList.get(eventDate);
    }

    private List<String> getList(LocalDate key) {
        return eventList.computeIfAbsent(key, k -> new ArrayList<>());
    }
}
