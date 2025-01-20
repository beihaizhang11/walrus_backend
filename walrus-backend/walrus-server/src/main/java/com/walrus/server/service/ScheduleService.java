package com.walrus.server.service;

import com.walrus.pojo.dto.ScheduleRequest;
import com.walrus.pojo.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule createSchedule(ScheduleRequest request, String createdBy);
    Schedule updateSchedule(Long id, ScheduleRequest request);
    void deleteSchedule(Long id);
    Schedule getSchedule(Long id);
    List<Schedule> getAllSchedules();
} 