package com.beck.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class MysqlConfig {

    private String type;
    private String driverClassName;
    private String url;
    private String usernamae;
    private String password;
}
