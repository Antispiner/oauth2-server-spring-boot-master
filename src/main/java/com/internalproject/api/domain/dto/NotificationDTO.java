package com.internalproject.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDTO {

    private String text;
    private String username;

    @AllArgsConstructor
    public enum Message {
        EMAIL_CHECK_MSG("Please, check your email and follow the link to verify your account."),
        RESET_PWD_EMAIL_MSG("Please, check your email and follow the link to reset your password."),
        SUCCESS_RESET_PWD_MSG("Your password has been successfully reset.");
        @Getter
        String value;
    }
}
