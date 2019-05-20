package com.example.parkinglot.config.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("swagger")
public class SwaggerConfigs {

    private String base;
    private String regex;
    private SwaggerResource resource;
    private Headers headers;

}
