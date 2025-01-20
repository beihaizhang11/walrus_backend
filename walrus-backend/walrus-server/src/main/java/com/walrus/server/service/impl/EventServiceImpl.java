package com.walrus.server.service.impl;

import com.walrus.pojo.dto.EventRequest;
import com.walrus.pojo.entity.Event;
import com.walrus.pojo.entity.Schedule;
import com.walrus.server.repository.EventRepository;
import com.walrus.server.repository.ScheduleRepository;
import com.walrus.server.service.EventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ScheduleRepository scheduleRepository;

    public EventServiceImpl(EventRepository eventRepository, ScheduleRepository scheduleRepository) {
        this.eventRepository = eventRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    @Transactional
    public Event createEvent(EventRequest request) {
        // 验证日程是否存在
        Schedule schedule = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow(() -> new RuntimeException("日程不存在"));

        Event event = new Event();
        event.setScheduleId(request.getScheduleId());
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setTargetDate(request.getTargetDate());
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public Event updateEvent(Long id, EventRequest request) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("事件不存在"));
        
        // 如果更改了所属日程，验证新日程是否存在
        if (!event.getScheduleId().equals(request.getScheduleId())) {
            scheduleRepository.findById(request.getScheduleId())
                    .orElseThrow(() -> new RuntimeException("日程不存在"));
            event.setScheduleId(request.getScheduleId());
        }

        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setTargetDate(request.getTargetDate());
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Event getEvent(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("事件不存在"));
    }

    @Override
    public List<Event> getEventsBySchedule(Long scheduleId) {
        return eventRepository.findByScheduleId(scheduleId);
    }
} 