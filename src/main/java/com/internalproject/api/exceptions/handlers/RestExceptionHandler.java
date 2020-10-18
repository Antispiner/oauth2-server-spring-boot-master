package com.internalproject.api.exceptions.handlers;

import com.internalproject.api.exceptions.models.DetailError;
import com.internalproject.api.exceptions.models.ValidationError;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        DetailError detailError = new DetailError();
        detailError.setTimeStamp(new Date().getTime());
        detailError.setStatus(status.value());
        detailError.setTitle("Message Not Readable");
        detailError.setDetail(ex.getMessage());
        detailError.setDeveloperMessage(ex.getClass().getName());
        return handleExceptionInternal(ex, detailError, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        DetailError detailError = new DetailError();
        detailError.setTimeStamp(new Date().getTime());
        detailError.setStatus(HttpStatus.BAD_REQUEST.value());
        detailError.setTitle("Validation Failed");
        detailError.setDetail("Input validation failed");
        detailError.setDeveloperMessage(ex.getClass().getName());
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fe : fieldErrors) {
            List<ValidationError> validationErrorList = detailError.getErrors().
                    computeIfAbsent(fe.getField(), k -> new ArrayList<>());
            ValidationError validationError = new ValidationError();
            validationError.setCode(fe.getCode());
            validationError.setMessage(messageSource.getMessage(fe, null));
            validationErrorList.add(validationError);
        }
        return handleExceptionInternal(ex, detailError, headers, status, request);
    }
}
