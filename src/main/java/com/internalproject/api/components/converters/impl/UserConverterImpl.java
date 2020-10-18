package com.internalproject.api.components.converters.impl;

import com.internalproject.api.components.converters.UserConverter;
import com.internalproject.api.domain.dto.UserDto;
import com.internalproject.api.model.entity.user.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public UserDto toDto(User entity) {
        UserDto dto = new UserDto();
        if (entity != null) {
            dto.setId(entity.getId());
            dto.setUsername(entity.getUserName());
            dto.setFirstName(entity.getFirstName());
            dto.setLastName(entity.getLastName());
            dto.setEmail(entity.getEmail());
        }
        return dto;
    }
}
