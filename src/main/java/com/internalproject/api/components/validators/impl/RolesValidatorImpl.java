package com.internalproject.api.components.validators.impl;
import com.internalproject.api.components.validators.RolesIsValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class RolesValidatorImpl implements ConstraintValidator<RolesIsValid, Set<String>> {
    @Override
    public boolean isValid(final Set<String> values, ConstraintValidatorContext context) {
        if (values == null) {
            return true;
        }
        for (String value : values) {
            return value.equalsIgnoreCase("role_user") ||
                    value.equalsIgnoreCase("role_admin");
        }
        return false;
    }
}
