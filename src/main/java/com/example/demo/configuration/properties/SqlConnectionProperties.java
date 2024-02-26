package com.example.demo.configuration.properties;

import lombok.Data;

@Data
public class SqlConnectionProperties {
    private String url;
    private String driver;
    private String username;
    private String password;
}

