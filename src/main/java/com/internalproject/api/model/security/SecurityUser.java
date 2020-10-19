package com.internalproject.api.model.security;

import com.internalproject.api.enums.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class SecurityUser implements UserDetails {


    private final String userName;
    private final String password;
    private final UserStatus status;
    private final Set<String> roles;

    public SecurityUser(String userName, String password, Set<String> roles, UserStatus status) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.status = status;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                         .map(SimpleGrantedAuthority::new)
                         .collect(toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
        return status.equals(UserStatus.ACTIVE);
    }
}
