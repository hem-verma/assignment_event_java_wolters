package com.wolters.assignment.event.repository;

import com.wolters.assignment.event.repository.entity.EventTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTypeRepository extends JpaRepository<EventTypeEntity, Long> {
    EventTypeEntity findByEventType(String eventType);
}
