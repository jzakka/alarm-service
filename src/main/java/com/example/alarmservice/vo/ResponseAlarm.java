package com.example.alarmservice.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseAlarm {
    private String to;
    private String title;
    private String subTitle;
    private String content;
    private LocalDateTime createAt;
}
