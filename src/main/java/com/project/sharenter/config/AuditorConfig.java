package com.project.sharenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//Enables JPA Auditing
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditorConfig {
    @Bean
    public AuditorAware<String> auditorAware(){
        return new AuditorAwareImpl();
    }
}