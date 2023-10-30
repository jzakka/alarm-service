package com.example.alarmservice.service;

import com.example.alarmservice.dto.AlarmDto;
import com.example.alarmservice.vo.RequestAlarm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {
    private final AlarmService alarmService;
    private final ModelMapper mapper;
    private final Environment env;

    @KafkaListener(topics = "${spring.kafka.topic-name}")
    public void alarm(@Payload RequestAlarm alarm) {
        final String SENDER = env.getProperty("email.from");

        AlarmDto alarmDto = mapper.map(alarm, AlarmDto.class);
        alarmDto.setFrom(SENDER);
        alarmDto.setCreateAt(LocalDateTime.now());

        alarmService.alarm(alarmDto);
    }
}
