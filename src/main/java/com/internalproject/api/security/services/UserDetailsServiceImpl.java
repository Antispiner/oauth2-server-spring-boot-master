package com.internalproject.api.security.services;

import com.internalproject.api.enums.UserStatus;
import com.internalproject.api.exceptions.ExceptionContent;
import com.internalproject.api.exceptions.models.ApiException;
import com.internalproject.api.model.entity.user.User;
import com.internalproject.api.repository.user.UserRepository;
import com.internalproject.api.model.security.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " could not be found");
        }
        String userName = user.getUserName();
        String password = user.getPassword();
        UserStatus status = user.getStatus();
        Set<String> roles = user.getRoles().stream()
                .map(role -> role.getRole().name())
                .collect(Collectors.toSet());

        return new SecurityUser(userName, password, roles, status);
    }
}
