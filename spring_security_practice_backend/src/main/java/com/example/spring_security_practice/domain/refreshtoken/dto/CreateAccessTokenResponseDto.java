package com.example.spring_security_practice.domain.refreshtoken.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAccessTokenResponseDto {
    private String accessToken;
}
