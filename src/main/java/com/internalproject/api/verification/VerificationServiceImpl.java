package com.internalproject.api.verification;

import com.internalproject.api.exceptions.models.EmailException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.UUID;

@Component
public class VerificationServiceImpl implements VerificationService {
    private static final String SEPARATOR = ".";
    private static final String SEPARATOR_REGEX = "\\.";
    private static final long VERIFICATION_KEY_LIFETIME_HOURS = 24;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public String generateKey(String userEmail) {
        return encode(LocalDateTime.now().format(FORMATTER)) + SEPARATOR + encode(userEmail) + SEPARATOR + generateEncodedUUID();
    }

    @Override
    public String extractUserEmail(String realKey) {
        String[] keyParts = realKey.split(SEPARATOR_REGEX);
        if (keyParts.length != 3) {
            //TODO добавить свой эксэпшн
            throw new EmailException(null, HttpStatus.BAD_REQUEST);
        }
        return decode(keyParts[1]);
    }

    @Override
    public boolean verifyAccount(String expectedKey, String realKey) {
        return expectedKey.equals(realKey) && !this.isVerificationKeyExpired(realKey);
    }

    @Override
    public boolean isVerificationKeyExpired(String key){
        return  this.getDifferenceInHours(key) >= VERIFICATION_KEY_LIFETIME_HOURS;
    }

    private long getDifferenceInHours(String key) {
        LocalDateTime fromDateTime = extractDateTime(key);
        LocalDateTime toDateTime = LocalDateTime.now();
        return ChronoUnit.HOURS.between(fromDateTime, toDateTime);
    }

    private LocalDateTime extractDateTime(String key){
        String encodedDateTime = key.split(SEPARATOR_REGEX)[0];
        String dateTime = decode(encodedDateTime);
        return LocalDateTime.parse(dateTime, FORMATTER);
    }

    private String encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

    private String decode(String value) {
        return new String(Base64.getDecoder().decode(value));
    }

    private String generateEncodedUUID() {
        String uuid = UUID.randomUUID().toString();
        return encode(uuid);
    }
}
