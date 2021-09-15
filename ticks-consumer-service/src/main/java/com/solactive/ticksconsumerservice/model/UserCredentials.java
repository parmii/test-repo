package com.solactive.ticksconsumerservice.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("credentials")
public class UserCredentials {
    private String username;
    private String password;
    private String role;
}
