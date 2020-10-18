package com.internalproject.api.exceptions.models;

import com.internalproject.api.exceptions.ExceptionContent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class ApiException  extends RuntimeException{

    private HttpStatus httpStatus;
    private String message;
    private ExceptionContent type;

    public ApiException(ExceptionContent type, HttpStatus status) {
        super(type.getText());
        this.message = type.getText();
        this.httpStatus = status;
        this.type = type;
    }

    public ApiException(ExceptionContent type, HttpStatus status, String message) {
        super(message);
        this.message = message;
        this.httpStatus = status;
        this.type = type;
    }
}
