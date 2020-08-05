package com.wolters.assignment.event.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "EVENT_TAB")
public class EventEntity {

    @Id
    @Column(name = "EVENT_ID")
    private Long eventId;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(referencedColumnName = "EVENT_TYPE_ID", name = "EVENT_TYPE_ID")
    private EventTypeEntity eventType;

    @Column(name = "EVENT_DATE")
    private LocalDate eventDate;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

}
