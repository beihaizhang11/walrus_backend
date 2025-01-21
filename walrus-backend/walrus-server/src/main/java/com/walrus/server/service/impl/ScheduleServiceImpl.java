package com.walrus.server.service.impl;

import com.walrus.pojo.dto.ScheduleRequest;
import com.walrus.pojo.entity.Schedule;
import com.walrus.server.repository.EventRepository;
import com.walrus.server.repository.ScheduleRepository;
import com.walrus.server.service.ScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final EventRepository eventRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, EventRepository eventRepository) {
        this.scheduleRepository = scheduleRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    @Transactional
    public Schedule createSchedule(ScheduleRequest request, String createdBy) {
        Schedule schedule = new Schedule();
        schedule.setTitle(request.getTitle());
        schedule.setDescription(request.getDescription());
        return scheduleRepository.save(schedule);
    }

    @Override
    @Transactional
    public Schedule updateSchedule(Long id, ScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("日程不存在"));
        
        schedule.setTitle(request.getTitle());
        schedule.setDescription(request.getDescription());
        return scheduleRepository.save(schedule);
    }

    @Override
    @Transactional
    public void deleteSchedule(Long id) {
        // 先删除该日程下的所有事件
        eventRepository.findByScheduleId(id).forEach(event -> 
            eventRepository.deleteById(event.getId())
        );
        // 然后删除日程
        scheduleRepository.deleteById(id);
    }

    @Override
    public Schedule getSchedule(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("日程不存在"));
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
} 