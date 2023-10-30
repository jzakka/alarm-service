package com.example.alarmservice.service;

import com.example.alarmservice.dto.AlarmDto;
import com.sendgrid.SendGrid;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SendgridMailAlarmServiceTest {
    @Autowired
    AlarmService alarmService;

    @MockBean
    SendGrid sendGrid;

    @Value("${email.from}")
    String MAIL_SENDER;

    @SneakyThrows
    @Test
    @DisplayName("메일 알람 서비스에서 api를 1회 호출해야함")
    void mailSendTest() {
        AlarmDto alarmDto = new AlarmDto(MAIL_SENDER,
                "receiver@test.com",
                "testMail",
                null,
                "thisistest",
                LocalDateTime.now());

        alarmService.alarm(alarmDto);

        verify(sendGrid, times(1)).api(any());
    }
}