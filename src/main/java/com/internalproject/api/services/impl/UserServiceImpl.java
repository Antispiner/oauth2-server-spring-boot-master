package com.internalproject.api.services.impl;

import com.internalproject.api.domain.requests.UserCreateRequest;
import com.internalproject.api.model.entity.user.Role;
import com.internalproject.api.model.entity.user.RoleType;
import com.internalproject.api.model.entity.user.User;
import com.internalproject.api.repository.user.RoleRepository;
import com.internalproject.api.repository.user.UserRepository;
import com.internalproject.api.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public User create(UserCreateRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Body of request for creating of user is empty!");
        }

        boolean exist = userRepository.existsUserByUserName(request.getUsername());
        if (exist) {
            throw new IllegalArgumentException("User with username " + request.getUsername() + " already existed!");
        }

        User user = new User();
        user.setUserName(request.getUsername());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setRoles(toUserRoles(request.getRoles()));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(toUserRoles(request.getRoles()));

        return userRepository.save(user);
    }

    private Set<Role> toUserRoles(Set<String> roles) {
        return roles.stream()
                .map(role -> roleRepository.findByRole(RoleType.valueOf(role)))
                .collect(Collectors.toSet());
    }
}
