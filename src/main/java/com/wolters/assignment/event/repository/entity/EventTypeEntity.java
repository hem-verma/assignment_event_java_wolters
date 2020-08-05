package com.wolters.assignment.event.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "EVENT_TYPE_TAB")
public class EventTypeEntity {

    @Id
    @Column(name = "EVENT_TYPE_ID")
    private Long eventId;

    @Column(name = "EVENT_TYPE")
    private String eventType;

}
