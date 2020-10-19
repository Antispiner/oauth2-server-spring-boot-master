package com.internalproject.api.domain.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class UserLoginRequest {
    private String username;
    private String password;
}
