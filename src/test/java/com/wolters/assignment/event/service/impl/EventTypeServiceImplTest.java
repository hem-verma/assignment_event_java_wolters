package com.wolters.assignment.event.service.impl;

import com.wolters.assignment.event.repository.EventTypeRepository;
import com.wolters.assignment.event.repository.entity.EventTypeEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventTypeServiceImplTest {
    @Mock
    private EventTypeRepository eventTypeRepository;
    @InjectMocks
    private EventTypeServiceImpl victim;

    @Test
    public void getEventTypesShouldReturnAllEventTypesPresentInDB() {
        EventTypeEntity entity1 = new EventTypeEntity(11L, "TRANSACTIONS");
        EventTypeEntity entity2 = new EventTypeEntity(22L, "TRANSACTIONS2");
        when(eventTypeRepository.findAll()).thenReturn(Arrays.asList(entity1, entity2));

        List<String> result = victim.getEventTypes();

        assertThat(result)
                .hasSize(2)
                .containsExactly("TRANSACTIONS","TRANSACTIONS2");
    }

    @Test
    public void getEventTypesShouldReturnEmptyListIfNothingIsPresentInDB() {
        when(eventTypeRepository.findAll()).thenReturn(Collections.emptyList());

        List<String> result = victim.getEventTypes();
        assertThat(result).hasSize(0);
    }
}
