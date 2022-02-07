package com.example.EnglishTest.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class User implements Serializable, UserDetails, GrantedAuthority {
    private String name;
    private String password;
    private String role;

    public User() {

    }

    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }


    @Override
    public String getAuthority() {
        return "ROLE_" + role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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
