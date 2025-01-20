package com.walrus.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "remote_events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scheduleid", nullable = false)
    private Long scheduleId;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(name = "targetdate")
    private Long targetDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheduleid", insertable = false, updatable = false)
    private Schedule schedule;
} 