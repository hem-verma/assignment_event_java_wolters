package com.wolters.assignment.event.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class EventRecord {

    private Long id;

    private String description;

    private String eventType;

    private LocalDate eventDate;

    private String customerName;
}
