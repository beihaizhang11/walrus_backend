package com.walrus.server.service;

import com.walrus.pojo.dto.EventRequest;
import com.walrus.pojo.entity.Event;

import java.util.List;

public interface EventService {
    Event createEvent(EventRequest request);
    Event updateEvent(Long id, EventRequest request);
    void deleteEvent(Long id);
    Event getEvent(Long id);
    List<Event> getEventsBySchedule(Long scheduleId);
} 