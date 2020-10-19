package com.internalproject.api.email.templates;

import com.internalproject.api.enums.Language;
import lombok.Getter;
import org.thymeleaf.context.Context;

@Getter
public abstract class AbstractEmailTemplate implements EmailTemplate {

    private final String htmlForm;

    protected final Context context = new Context();

    AbstractEmailTemplate(String htmlForm) {
        this.htmlForm = htmlForm;
    }

    @Override
    public void setLanguage(Language language) {
        context.setVariable("language", language.name());
    }
}
