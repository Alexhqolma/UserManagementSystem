package com.example.usermanagementsystem.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LatinValidator implements ConstraintValidator<Latin, String> {
    private static final String LATIN_REGEX = "^[A-Za-z]$";

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        return userName.matches(LATIN_REGEX);
    }
}
