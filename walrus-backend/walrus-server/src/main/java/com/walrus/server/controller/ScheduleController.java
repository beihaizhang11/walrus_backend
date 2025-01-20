package com.walrus.server.controller;

import com.walrus.common.result.Result;
import com.walrus.pojo.dto.ScheduleRequest;
import com.walrus.pojo.entity.Schedule;
import com.walrus.server.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "日程管理", description = "日程相关接口")
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Operation(summary = "创建日程")
    @PostMapping
    public Result<Schedule> createSchedule(@RequestBody ScheduleRequest request) {
        Schedule schedule = scheduleService.createSchedule(request, "admin"); // TODO: 从JWT中获取用户信息
        return Result.success(schedule);
    }

    @Operation(summary = "更新日程")
    @PutMapping("/{id}")
    public Result<Schedule> updateSchedule(@PathVariable("id") Long id, @RequestBody ScheduleRequest request) {
        Schedule schedule = scheduleService.updateSchedule(id, request);
        return Result.success(schedule);
    }

    @Operation(summary = "删除日程")
    @DeleteMapping("/{id}")
    public Result<Void> deleteSchedule(@PathVariable("id") Long id) {
        scheduleService.deleteSchedule(id);
        return Result.success();
    }

    @Operation(summary = "获取日程详情")
    @GetMapping("/{id}")
    public Result<Schedule> getSchedule(@PathVariable("id") Long id) {
        Schedule schedule = scheduleService.getSchedule(id);
        return Result.success(schedule);
    }

    @Operation(summary = "获取所有日程")
    @GetMapping
    public Result<List<Schedule>> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return Result.success(schedules);
    }
} 