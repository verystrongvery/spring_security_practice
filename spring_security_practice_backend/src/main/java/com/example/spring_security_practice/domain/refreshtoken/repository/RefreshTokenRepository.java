package com.example.spring_security_practice.domain.refreshtoken.repository;

import com.example.spring_security_practice.domain.refreshtoken.entity.RefreshToken;
import com.example.spring_security_practice.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findRefreshTokenByUsers(Users users);
}
