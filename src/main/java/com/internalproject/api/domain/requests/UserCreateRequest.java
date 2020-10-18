package com.internalproject.api.domain.requests;

import com.internalproject.api.components.validators.EmailIsValid;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;


@Getter
@Setter
public class UserCreateRequest {

    @NotNull
    @Size(min = 3, max = 20)
    private String username;
    @Size(min = 1, max = 30)
    private String firstName;
    @Size(min = 1, max = 30)
    private String lastName;
    @NotNull
    @Size(min = 6, max = 50)
    private String password;
    @NotBlank
    @EmailIsValid
    private String email;
}
