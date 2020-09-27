package com.internalproject.api.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto implements BaseDto {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private Set<String> roles;

}
