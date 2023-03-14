package com.epam.engx.jam.command;

import com.epam.engx.jam.task1.FactorialActionAlgorithm;
import com.epam.engx.jam.task1.FactorialLinearAlgorithm;
import com.epam.engx.jam.task1.FactorialTaskAlgorithm;
import jakarta.validation.constraints.Min;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.math.BigInteger;

@ShellComponent
public record FactorialCommand(
    FactorialLinearAlgorithm linearAlgorithm,
    FactorialActionAlgorithm actionAlgorithm,
    FactorialTaskAlgorithm taskAlgorithm) {

    @ShellMethod("Factorial of non-negative integer by linear algorithm")
    public BigInteger factorial(@Min(0) int number) {
        return linearAlgorithm.apply(number);
    }

    @ShellMethod("Factorial calculation using class RecursiveAction")
    public BigInteger factorialAction(@Min(0) int number) {
        return actionAlgorithm.apply(number);
    }

    @ShellMethod("Factorial calculation using class RecursiveTask")
    public BigInteger factorialTask(@Min(0) int number) {
        return taskAlgorithm.apply(number);
    }
}
