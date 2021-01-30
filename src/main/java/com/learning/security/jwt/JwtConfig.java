package com.learning.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {
    private Integer tokenExpirationAfterDays;
    private String secret;
    private String tokenPrefix;

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
