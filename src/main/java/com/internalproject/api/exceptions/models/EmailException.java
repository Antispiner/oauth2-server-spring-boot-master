package com.internalproject.api.exceptions.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
public class EmailException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;
}
