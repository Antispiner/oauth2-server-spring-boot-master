package com.internalproject.api.email.factory;

import com.internalproject.api.email.templates.EmailTemplateImpl;

public interface EmailFactory {
    EmailTemplateImpl getAccountVerifiedTemplate();
}
