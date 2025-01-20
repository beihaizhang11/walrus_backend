package com.walrus.server.repository;

import com.walrus.pojo.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByScheduleId(Long scheduleId);
} 