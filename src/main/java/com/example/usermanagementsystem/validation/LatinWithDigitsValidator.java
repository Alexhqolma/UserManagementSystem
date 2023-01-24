package com.example.usermanagementsystem.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LatinWithDigitsValidator implements ConstraintValidator<LatinWithDigits, String> {
    private static final String LATIN_DIGITS_REGEX = "^[A-Za-z0-9]$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return password.matches(LATIN_DIGITS_REGEX);
    }
}
