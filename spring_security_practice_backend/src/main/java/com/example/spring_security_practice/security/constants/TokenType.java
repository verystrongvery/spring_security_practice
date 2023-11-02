package com.example.spring_security_practice.security.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TokenType {
    ACCESS_TOKEN("access_token", 600),
    REFRESH_TOKEN("refresh_token", 604800),
    AUTHENTICATION_DONE("authentication_done", 5);

    private String name;
    private int seconds;
}
