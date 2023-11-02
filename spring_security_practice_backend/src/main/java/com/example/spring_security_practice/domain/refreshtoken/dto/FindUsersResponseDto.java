package com.example.spring_security_practice.domain.refreshtoken.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindUsersResponseDto {
    private Long usersId;
    private String email;
    private String name;
    private String picture;
}