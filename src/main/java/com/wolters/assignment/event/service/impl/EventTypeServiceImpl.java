package com.wolters.assignment.event.service.impl;

import com.wolters.assignment.event.repository.EventTypeRepository;
import com.wolters.assignment.event.repository.entity.EventTypeEntity;
import com.wolters.assignment.event.service.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventTypeServiceImpl implements EventTypeService {

    private final EventTypeRepository eventTypeRepository;

    @Autowired
    public EventTypeServiceImpl(EventTypeRepository eventTypeRepository) {
        this.eventTypeRepository = eventTypeRepository;
    }

    @Override
    public List<String> getEventTypes() {
        return eventTypeRepository.findAll().stream()
                .map(EventTypeEntity::getEventType)
                .collect(Collectors.toList());
    }
}
