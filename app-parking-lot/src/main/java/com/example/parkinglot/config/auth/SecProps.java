package com.example.parkinglot.config.auth;

import java.util.List;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("security")
public class SecProps {

    private List<String> secret;
    private String authorizationUrl;
    private Integer sessionTimeout;
    private String sessionTimeoutType;
    private String accessToken;
    private String tokenType;
    private String utid;
    private String jti;
    private String failureMessage;
    private String badCredentialMessage;

}
