package com.example.parkinglot.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = -1051529419998403724L;

    private Long id;
    private String name;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    /**
     *
     * @param username
     * @param password
     */
    public UserPrincipal(Long id, String username, String password,
                         Collection<? extends GrantedAuthority> authorities) {
        this.id          = id;
        this.username    = username;
        this.password    = password;
        this.authorities = authorities;
    }

    public UserPrincipal(String username,
                         Collection<? extends GrantedAuthority> authorities) {
        this.username    = username;
        this.authorities = authorities;
    }

    public UserPrincipal(Long id, String name, String username, String password,
                         Collection<? extends GrantedAuthority> authorities) {
        this.id          = id;
        this.name        = name;
        this.username    = username;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

