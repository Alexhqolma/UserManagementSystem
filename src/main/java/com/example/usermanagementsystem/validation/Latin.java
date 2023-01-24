package com.example.usermanagementsystem.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LatinValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Latin {
    String message() default "You used not latin letters in username";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
