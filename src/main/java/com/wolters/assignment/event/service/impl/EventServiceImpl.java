package com.wolters.assignment.event.service.impl;

import com.wolters.assignment.event.exception.EventNotFoundException;
import com.wolters.assignment.event.model.EventRecord;
import com.wolters.assignment.event.repository.EventRepository;
import com.wolters.assignment.event.repository.EventTypeRepository;
import com.wolters.assignment.event.repository.entity.EventEntity;
import com.wolters.assignment.event.repository.entity.EventTypeEntity;
import com.wolters.assignment.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventTypeRepository eventTypeRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, EventTypeRepository eventTypeRepository) {
        this.eventRepository = eventRepository;
        this.eventTypeRepository = eventTypeRepository;
    }

    @Override
    public List<EventRecord> getEventsByCustomerName(String customerName) {
        return eventRepository.findByCustomerNameIgnoreCaseOrderByEventDateDesc(customerName)
                .stream()
                .map(this::convertEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventRecord> getEvents() {
        return eventRepository.findByOrderByEventDateAsc()
                .stream()
                .map(this::convertEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventRecord> getEventsByCustomerNameAndEventType(String customerName, String eventType) {
        EventTypeEntity eventTypeEntity = eventTypeRepository.findByEventType(eventType);
        return eventRepository.findByCustomerNameIgnoreCaseAndEventTypeOrderByEventDateDesc(customerName, eventTypeEntity)
                .stream()
                .map(this::convertEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public EventRecord getEventById(Long id) {
        EventEntity eventEntity = eventRepository.findByEventId(id).orElseThrow(() -> new EventNotFoundException(""));
        return convertEntityToModel(eventEntity);
    }

    private EventRecord convertEntityToModel(EventEntity event) {
        return EventRecord.builder()
                .id(event.getEventId())
                .description(event.getDescription())
                .eventType(event.getEventType().getEventType())
                .customerName(event.getCustomerName())
                .eventDate(event.getEventDate())
                .build();
    }

}
