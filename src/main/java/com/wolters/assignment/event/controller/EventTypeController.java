package com.wolters.assignment.event.controller;

import com.wolters.assignment.event.service.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eventTypes")
public class EventTypeController {

    private final EventTypeService eventTypeService;

    @Autowired
    public EventTypeController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    /**
     * Returns list of event types
     * @return List of types
     */
    @GetMapping(value = "")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public List<String> getEventTypes() {
        return eventTypeService.getEventTypes();
    }
}
