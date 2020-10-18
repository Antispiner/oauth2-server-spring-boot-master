package com.internalproject.api.email.templates;

import lombok.Getter;

@Getter
public class EmailTemplateImpl extends AbstractEmailTemplate implements EmailTemplate {

    public EmailTemplateImpl(String htmlForm) {
        super(htmlForm);
    }

    public void setLink(String link) {
        context.setVariable("link", link);
    }
}
