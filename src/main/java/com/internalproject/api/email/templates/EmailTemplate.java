package com.internalproject.api.email.templates;

import com.internalproject.api.enums.Language;
import org.thymeleaf.context.Context;

public interface EmailTemplate {
    String getHtmlForm();

    Context getContext();

    void setLanguage(Language language);

    void setLink(String link);
}
