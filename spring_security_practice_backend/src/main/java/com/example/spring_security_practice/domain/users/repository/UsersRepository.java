package com.example.spring_security_practice.domain.users.repository;

import com.example.spring_security_practice.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUsersByEmail(String email);
}
