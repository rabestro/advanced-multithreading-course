package com.epam.engx.jam.command;

import com.epam.engx.jam.task1.FactorialLinearAlgorithm;
import jakarta.validation.constraints.Min;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.math.BigInteger;

@ShellComponent
public record FactorialCommand(
    FactorialLinearAlgorithm linearAlgorithm) {

    @ShellMethod("Factorial of non-negative integer by linear algorithm")
    public BigInteger fac(@Min(0) int number) {
        return linearAlgorithm.apply(number);
    }
}
