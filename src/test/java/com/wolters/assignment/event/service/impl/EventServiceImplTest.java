package com.wolters.assignment.event.service.impl;

import com.wolters.assignment.event.exception.EventNotFoundException;
import com.wolters.assignment.event.model.EventRecord;
import com.wolters.assignment.event.repository.EventRepository;
import com.wolters.assignment.event.repository.EventTypeRepository;
import com.wolters.assignment.event.repository.entity.EventEntity;
import com.wolters.assignment.event.repository.entity.EventTypeEntity;
import org.assertj.core.groups.Tuple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceImplTest {
    @Mock
    private EventRepository eventRepository;
    @Mock
    private EventTypeRepository eventTypeRepository;
    @InjectMocks
    private EventServiceImpl victim;
    EventTypeEntity entityType1 = new EventTypeEntity(11L, "TRANSACTIONS");

    @Test
    public void getEventsByCustomerNameShouldReturnAllEventsBytThatCustomer() {
        EventEntity entity1 = new EventEntity(1L, "event1", entityType1, LocalDate.now(), "cust1");
        EventEntity entity2 = new EventEntity(2L, "event2", entityType1, LocalDate.now(), "cust1");
        when(eventRepository.findByCustomerNameIgnoreCaseOrderByEventDateDesc("cust1")).thenReturn(Arrays.asList(entity1, entity2));

        List<EventRecord> result = victim.getEventsByCustomerName("cust1");

        assertThat(result)
                .hasSize(2)
                .extracting(EventRecord::getId, EventRecord::getDescription)
                .containsExactly(Tuple.tuple(1L, "event1")
                        , Tuple.tuple(2L, "event2"));
    }

    @Test
    public void getEventsByCustomerNameShouldReturnEmptyListIfNothingIsPresentInDB() {
        when(eventRepository.findByCustomerNameIgnoreCaseOrderByEventDateDesc("cust1")).thenReturn(Collections.emptyList());

        List<EventRecord> result = victim.getEventsByCustomerName("cust1");

        assertThat(result).isEmpty();
    }

    @Test
    public void getEventsShouldReturnAllEvents() {
        EventEntity entity1 = new EventEntity(1L, "event1", entityType1, LocalDate.now(), "cust1");
        EventEntity entity2 = new EventEntity(2L, "event2", entityType1, LocalDate.now(), "cust1");
        when(eventRepository.findByOrderByEventDateAsc()).thenReturn(Arrays.asList(entity1, entity2));

        List<EventRecord> result = victim.getEvents();

        assertThat(result)
                .hasSize(2)
                .extracting(EventRecord::getId, EventRecord::getDescription)
                .containsExactly(Tuple.tuple(1L, "event1")
                        , Tuple.tuple(2L, "event2"));
    }

    @Test
    public void getEventsShouldReturnEMptyListIfNothingIsPresentInDB() {
        when(eventRepository.findByOrderByEventDateAsc()).thenReturn(Collections.emptyList());

        List<EventRecord> result = victim.getEvents();

        assertThat(result).isEmpty();

    }

    @Test
    public void getEventsByCustomerNameAndEventTypeShouldReturnAllEventsBytThatCustomerAndEventType() {
        when(eventTypeRepository.findByEventType("TRANSACTIONS")).thenReturn(entityType1);
        EventEntity entity1 = new EventEntity(1L, "event1", entityType1, LocalDate.now(), "cust1");
        EventEntity entity2 = new EventEntity(2L, "event2", entityType1, LocalDate.now(), "cust1");
        when(eventRepository.findByCustomerNameIgnoreCaseAndEventTypeOrderByEventDateDesc("cust1", entityType1))
                .thenReturn(Arrays.asList(entity1, entity2));

        List<EventRecord> result = victim.getEventsByCustomerNameAndEventType("cust1", "TRANSACTIONS");

        assertThat(result)
                .hasSize(2)
                .extracting(EventRecord::getId, EventRecord::getDescription)
                .containsExactly(Tuple.tuple(1L, "event1")
                        , Tuple.tuple(2L, "event2"));
    }

    @Test
    public void getEventsByCustomerNameAndEventTypeShouldReturnEmptyListIfNothingIsPresentInDB() {
        when(eventTypeRepository.findByEventType("TRANSACTIONS")).thenReturn(entityType1);
        when(eventRepository.findByCustomerNameIgnoreCaseAndEventTypeOrderByEventDateDesc("cust1", entityType1)).thenReturn(Collections.emptyList());

        List<EventRecord> result = victim.getEventsByCustomerNameAndEventType("cust1", "TRANSACTIONS");

        assertThat(result).isEmpty();
    }

    @Test
    public void getEventByIdShouldReturnEventDetailsForThatID() {
        EventEntity entity1 = new EventEntity(1L, "event1", entityType1, LocalDate.now(), "cust1");
        when(eventRepository.findByEventId(1L)).thenReturn(Optional.of(entity1));

        EventRecord result = victim.getEventById(1L);

        assertThat(result)
                .extracting(EventRecord::getId, EventRecord::getDescription)
                .containsExactly(1L, "event1");
    }

    @Test(expected = EventNotFoundException.class)
    public void getEventByIdShouldReturnErrorIFEventIdNotExixts() {
        when(eventRepository.findByEventId(1L)).thenReturn(Optional.empty());

        victim.getEventById(1L);
    }
}
