package com.wolters.assignment.event.repository;

import com.wolters.assignment.event.repository.entity.EventEntity;
import com.wolters.assignment.event.repository.entity.EventTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    Optional<EventEntity> findByEventId(Long id);

    List<EventEntity> findByOrderByEventDateAsc();

    List<EventEntity> findByCustomerNameIgnoreCaseOrderByEventDateDesc(String customerName);

    List<EventEntity> findByCustomerNameIgnoreCaseAndEventTypeOrderByEventDateDesc(String customerName, EventTypeEntity eventType);
}
