package com.example.demo.configuration;

import com.example.demo.configuration.properties.SqlConnectionProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
public class MySQLConfiguration {

    private final SqlConnectionProperties connectionProperties;

    public MySQLConfiguration(SqlConnectionProperties connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(connectionProperties.getDriver());
        dataSource.setUrl(connectionProperties.getUrl());
        dataSource.setUsername(connectionProperties.getUsername());
        dataSource.setPassword(connectionProperties.getPassword());
        return dataSource;
    }
}
