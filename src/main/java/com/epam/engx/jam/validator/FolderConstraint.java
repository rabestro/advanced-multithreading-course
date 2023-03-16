package com.epam.engx.jam.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public final class FolderConstraint implements ConstraintValidator<Folder, String> {

    @Override
    public boolean isValid(String path, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return Files.isDirectory(Paths.get(path));
        } catch (InvalidPathException ex) {
            return false;
        }
    }
}
