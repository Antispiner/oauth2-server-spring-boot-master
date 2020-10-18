package com.internalproject.api.verification;

public interface VerificationService {
    String generateKey(String userEmail);

    String extractUserEmail(String realKey);

    boolean verifyAccount(String expectedKey, String realKey);

    boolean isVerificationKeyExpired(String key);
}
