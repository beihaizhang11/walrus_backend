package com.walrus.pojo.dto;

import lombok.Data;

@Data
public class EventRequest {
    private Long scheduleId;
    private String title;
    private String description;
    private Long targetDate;
} 