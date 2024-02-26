package com.example.demo.configuration;

import com.example.demo.configuration.properties.SqlConnectionProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationSettingsConfiguration {


    @Bean
    @ConfigurationProperties(prefix = "sql")
    public SqlConnectionProperties sqlConnectionProperties() {
        return new SqlConnectionProperties();
    }
}
