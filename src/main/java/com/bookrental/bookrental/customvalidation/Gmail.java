package com.bookrental.bookrental.customvalidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = GmailValidator.class)
public @interface Gmail {

    String message() default "Invalid email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String regexp() default "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

    boolean gmailOnly() default false;

}
