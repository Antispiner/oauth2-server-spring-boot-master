package com.internalproject.api.domain.requests;

import com.internalproject.api.components.validators.EmailIsValid;
import com.internalproject.api.components.validators.RolesIsValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Set;

@Getter
@Setter
public class UserCreateRequest {

    @NotNull
    @Size(min = 3, max = 20)
    private String username;

    private String firstName;

    private String lastName;

    @NotNull
    @Size(min = 6, max = 50)
    private String password;

    @NotBlank
    @EmailIsValid
    private String email;

    @NotEmpty
    @RolesIsValid
    private Set<@NotBlank @Size(min = 9)  String> roles;
}
