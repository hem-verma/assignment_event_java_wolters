package com.wolters.assignment.event.controller;

import com.wolters.assignment.event.model.EventRecord;
import com.wolters.assignment.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/events")
@Validated
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Returns events for a customer
     * @param customerName name of the customer
     * @return List of events
     */
    @GetMapping(value = "/customers/{customerName}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public List<EventRecord> getEventsByCustomerName(@NotNull @PathVariable String customerName) {
        return eventService.getEventsByCustomerName(customerName);
    }

    /**
     * get all events present in Db
     * @return List of events
     */
    @GetMapping(value = "")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public List<EventRecord> getEvents() {
        return eventService.getEvents();
    }

    /**
     * get event for a corresponding id
     * @param id event id
     * @return Event record
     */
    @GetMapping(value = "/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public EventRecord getEventById(@NotNull @PathVariable Long id) {
        return eventService.getEventById(id);
    }

    /**
     * Return events for a specified customer and for a specified type
     * @param customerName name of customer
     * @param eventType event type
     * @return List of events
     */
    @GetMapping(value = "/customers/{customerName}/types/{eventType}")
    @ResponseStatus(HttpStatus.OK)
    public List<EventRecord> getEvents(@NotNull @PathVariable String customerName,
                                       @NotNull @PathVariable String eventType) {
        return eventService.getEventsByCustomerNameAndEventType(customerName, eventType);
    }
}
