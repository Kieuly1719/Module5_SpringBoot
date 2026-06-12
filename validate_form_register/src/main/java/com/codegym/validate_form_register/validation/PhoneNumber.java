package com.codegym.validate_form_register.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumber {
    String message() default "Số điện thoại phải gồm 10 chữ số và bắt đầu bằng 0";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
