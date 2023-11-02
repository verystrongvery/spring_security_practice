package com.example.spring_security_practice.domain.refreshtoken.entity;

import com.example.spring_security_practice.domain.users.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue
    private Long refreshTokenId;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="users_id", unique = true)
    private Users users;
    @Column(nullable = false)
    private String token;

    public RefreshToken(Users users, String token) {
        this.users = users;
        this.token = token;
    }
}
