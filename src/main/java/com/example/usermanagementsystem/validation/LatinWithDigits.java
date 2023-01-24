package com.example.usermanagementsystem.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LatinWithDigitsValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LatinWithDigits {
    String message() default "You have to use only latin letters or latin letters with digits in username";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
