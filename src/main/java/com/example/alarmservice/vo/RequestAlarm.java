package com.example.alarmservice.vo;

import lombok.Data;

@Data
public class RequestAlarm {
    private String to;
    private String title;
    private String content;
}
