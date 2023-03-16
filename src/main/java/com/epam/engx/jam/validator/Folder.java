package com.epam.engx.jam.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;

@NotBlank
@Target({PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FolderConstraint.class})
public @interface Folder {
    String message() default "must be valid path to a folder";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
