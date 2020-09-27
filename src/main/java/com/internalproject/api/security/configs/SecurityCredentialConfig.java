package com.internalproject.api.security.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app-security")
public class SecurityCredentialConfig {

    private String appClientId;
    private String appClientSecret;

    public String getAppClientId() {
        return appClientId;
    }

    public void setAppClientId(String appClientId) {
        this.appClientId = appClientId;
    }

    public String getAppClientSecret() {
        return appClientSecret;
    }

    public void setAppClientSecret(String appClientSecret) {
        this.appClientSecret = appClientSecret;
    }
}
