package com.bookrental.bookrental.customvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class GmailValidator implements ConstraintValidator<Gmail, String> {
    private String regexp;
    private boolean gmailOnly;

    @Override
    public void initialize(Gmail gmail) {
        this.regexp = gmail.regexp();
        this.gmailOnly = gmail.gmailOnly();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        if (!value.matches(regexp)) {
            return false;
        }

        if (gmailOnly && !value.endsWith("@gmail.com")) {
            return false;
        }
        return true;
    }
}
