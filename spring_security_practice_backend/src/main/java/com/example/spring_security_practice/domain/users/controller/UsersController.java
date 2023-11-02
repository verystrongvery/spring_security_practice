package com.example.spring_security_practice.domain.users.controller;

import com.example.spring_security_practice.domain.refreshtoken.dto.FindUsersResponseDto;
import com.example.spring_security_practice.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UsersController {
    private final UsersService usersService;

    @GetMapping
    public ResponseEntity<FindUsersResponseDto> findUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) {
            return ResponseEntity.ok().build();
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        FindUsersResponseDto findUsersResponseDto = usersService.findUsers(userDetails.getUsername());
        return ResponseEntity.ok(findUsersResponseDto);
    }
}
