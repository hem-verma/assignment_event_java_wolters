package com.wolters.assignment.event.service;

import com.wolters.assignment.event.model.EventRecord;

import java.util.List;

public interface EventService {
    List<EventRecord> getEventsByCustomerName(String customerName);

    List<EventRecord> getEvents();

    List<EventRecord> getEventsByCustomerNameAndEventType(String customerName, String eventType);

    EventRecord getEventById(Long id);
}
