package com.internalproject.api.email;

import com.internalproject.api.email.templates.EmailTemplate;

public interface EmailService {
    void sendEmail(String to, String  subject, EmailTemplate template);
}
