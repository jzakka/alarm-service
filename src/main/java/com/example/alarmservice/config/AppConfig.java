package com.example.alarmservice.config;

import com.sendgrid.SendGrid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final Environment env;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }

    @Bean
    public SendGrid sendGrid() {
        final String API_KEY = env.getProperty("spring.sendgrid.api-key");
        return new SendGrid(API_KEY);
    }
}
