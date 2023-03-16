package com.epam.engx.jam.command;

import com.epam.engx.jam.task1.FactorialActionAlgorithm;
import com.epam.engx.jam.task1.FactorialLinearAlgorithm;
import com.epam.engx.jam.task1.FactorialParallelStream;
import com.epam.engx.jam.task1.FactorialTaskAlgorithm;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class FactorialCommand {
    private static final String THRESHOLD = "10";
    private final FactorialLinearAlgorithm linearAlgorithm;
    private final FactorialActionAlgorithm actionAlgorithm;
    private final FactorialTaskAlgorithm taskAlgorithm;
    private final FactorialParallelStream parallelStream;
    private final BigIntegerToStringConverter formatter;

    @ShellMethod("Factorial of non-negative integer by linear algorithm")
    public String factorial(int number) {
        return formatter.convert(linearAlgorithm.apply(number));
    }

    @ShellMethod("Factorial calculation using class RecursiveAction")
    public String factorialAction(
        @Min(0) int number,
        @Min(2) @ShellOption(defaultValue = THRESHOLD) int threshold
    ) {
        actionAlgorithm.setThreshold(threshold);
        return formatter.convert(actionAlgorithm.apply(number));
    }

    @ShellMethod("Factorial calculation using class RecursiveTask")
    public String factorialTask(
        @Min(0) int number,
        @Min(2) @ShellOption(defaultValue = THRESHOLD) int threshold
    ) {
        taskAlgorithm.setThreshold(threshold);
        return formatter.convert(taskAlgorithm.apply(number));
    }

    @ShellMethod("Factorial calculation using parallel stream")
    public String factorialParallel(@Min(0) int number) {
        return formatter.convert(parallelStream.apply(number));
    }
}
