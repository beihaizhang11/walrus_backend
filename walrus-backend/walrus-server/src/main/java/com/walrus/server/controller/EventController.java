package com.walrus.server.controller;

import com.walrus.common.result.Result;
import com.walrus.pojo.dto.EventRequest;
import com.walrus.pojo.entity.Event;
import com.walrus.server.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "事件管理", description = "事件相关接口")
@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "创建事件")
    @PostMapping
    public Result<Event> createEvent(@RequestBody EventRequest request) {
        Event event = eventService.createEvent(request);
        return Result.success(event);
    }

    @Operation(summary = "更新事件")
    @PutMapping("/{id}")
    public Result<Event> updateEvent(@PathVariable("id") Long id, @RequestBody EventRequest request) {
        Event event = eventService.updateEvent(id, request);
        return Result.success(event);
    }

    @Operation(summary = "删除事件")
    @DeleteMapping("/{id}")
    public Result<Void> deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);
        return Result.success();
    }

    @Operation(summary = "获取事件详情")
    @GetMapping("/{id}")
    public Result<Event> getEvent(@PathVariable("id") Long id) {
        Event event = eventService.getEvent(id);
        return Result.success(event);
    }

    @Operation(summary = "获取日程下的所有事件")
    @GetMapping("/schedule/{scheduleId}")
    public Result<List<Event>> getEventsBySchedule(@PathVariable("scheduleId") Long scheduleId) {
        List<Event> events = eventService.getEventsBySchedule(scheduleId);
        return Result.success(events);
    }
} 