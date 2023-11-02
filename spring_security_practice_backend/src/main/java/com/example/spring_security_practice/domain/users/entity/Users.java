package com.example.spring_security_practice.domain.users.entity;

import com.example.spring_security_practice.domain.refreshtoken.dto.FindUsersResponseDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;

@Entity
@Getter
public class Users implements UserDetails {
    @Id
    @GeneratedValue
    private Long usersId;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String picture;

    public static Users createUsers(String email, String name, String picture) {
        Users users = new Users();
        users.email = email;
        users.name = name;
        users.picture = picture;
        return users;
    }

    public FindUsersResponseDto toFindUsersResponseDto() {
        return new FindUsersResponseDto(usersId, email, name, picture);
    }

    @Override
    public int hashCode() {
        return Long.hashCode(usersId);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
