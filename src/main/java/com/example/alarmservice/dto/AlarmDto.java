package com.example.alarmservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlarmDto {
    private String from;
    private String to;
    private String title;
    private String subTitle;
    private String content;
    private LocalDateTime createAt;
}
