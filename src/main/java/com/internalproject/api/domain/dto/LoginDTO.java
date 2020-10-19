package com.internalproject.api.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class LoginDTO {
    private String accessToken;
    private String refreshToken;
}
