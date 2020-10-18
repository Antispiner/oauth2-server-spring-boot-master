package com.internalproject.api.email.factory;

import com.internalproject.api.email.templates.EmailTemplateImpl;

import org.springframework.stereotype.Component;

@Component
public class EmailFactoryImpl implements EmailFactory {

    private static final String HTML_VERIFY_EMAIL_PAGE = "email/verify-email";

    @Override
    public EmailTemplateImpl getAccountVerifiedTemplate() {
        return new EmailTemplateImpl(HTML_VERIFY_EMAIL_PAGE);
    }
}
