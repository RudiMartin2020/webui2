package com.example.demo.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TestUser {
    private final String username = "user";
    private final String password = "pass";
    private final List<String> roles = new ArrayList<>();

    public TestUser() {
        roles.add("ROLE_USER");
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
