package com.internalproject.api.components.validators;

import com.internalproject.api.components.validators.impl.RolesValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = RolesValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface RolesIsValid {
    String message() default "{Roles.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
