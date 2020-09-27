package com.internalproject.api.components.validators;
import com.internalproject.api.components.validators.impl.EmailValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = EmailValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailIsValid {
    String message() default "{Email.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
