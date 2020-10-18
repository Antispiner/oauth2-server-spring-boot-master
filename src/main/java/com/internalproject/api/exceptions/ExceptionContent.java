package com.internalproject.api.exceptions;

import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@Getter
public enum ExceptionContent {

    INVALID_VERIFICATION_KEY("api.exception.invalidVerificationKey"),
    CHECK_EMAIL_AND_VALID("api.exception.checkEmailAndValid"),
    USER_INACTIVE_ERROR("api.exception.accountStatusNotActive");


    private String path;
    static MessageSource messageSource;

    ExceptionContent(String path) {
        this.path = path;
    }

    public static void setMessageSource(MessageSource messageSource) {
        ExceptionContent.messageSource = messageSource;
    }

    public String getText(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(path, null,locale);
    }
    public String getText(Locale locale){
        return messageSource.getMessage(path, null,locale);
    }
}
