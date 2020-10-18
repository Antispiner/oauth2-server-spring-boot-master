package com.internalproject.api.configs;

import com.internalproject.api.exceptions.ExceptionContent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import javax.annotation.PostConstruct;

@Configuration
public class ApiConfig {
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("i18n/exceptions");
        source.setDefaultEncoding("UTF-8");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

    @PostConstruct
    public void init(){
        ExceptionContent.setMessageSource(messageSource());
    }
}
