package com.example.alarmservice.service;

import com.example.alarmservice.dto.AlarmDto;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendgridMailAlarmService implements AlarmService {
    private final SendGrid sendGrid;

    @Override
    public void alarm(AlarmDto alarmDto) {
        sendMail(alarmDto);
    }

    private void sendMail(AlarmDto alarmDto) {
        Mail mail = buildMail(alarmDto);

        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        try {
            request.setBody(mail.build());
            sendGrid.api(request);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private Mail buildMail(AlarmDto alarmDto) {
        Email from = new Email(alarmDto.getFrom());
        Email to = new Email(alarmDto.getTo());
        String subject = alarmDto.getTitle();
        Content content = new Content("text/plain", alarmDto.getContent());

        return new Mail(from, subject, to, content);
    }
}
