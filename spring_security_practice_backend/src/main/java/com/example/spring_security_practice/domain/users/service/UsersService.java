package com.example.spring_security_practice.domain.users.service;

import com.example.spring_security_practice.domain.refreshtoken.dto.FindUsersResponseDto;
import com.example.spring_security_practice.domain.users.costants.UsersErrorMessage;
import com.example.spring_security_practice.domain.users.entity.Users;
import com.example.spring_security_practice.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UsersService {
    private final UsersRepository usersRepository;
    public FindUsersResponseDto findUsers(String email) {
        Users users = usersRepository.findUsersByEmail(email).orElseThrow(() -> new NoSuchElementException(UsersErrorMessage.NOT_FOUND_USER_EMAIL));
        return users.toFindUsersResponseDto();
    }
}
