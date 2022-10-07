package com.eluanps.travelapp.config;

import com.eluanps.travelapp.service.EmailService;
import com.eluanps.travelapp.service.MockEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class TestConfig {
    
    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }
    
}
