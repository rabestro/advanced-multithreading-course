package com.epam.engx.jam.command;

import com.epam.engx.jam.task6a.FibonacciForkJoinAlgorithm;
import com.epam.engx.jam.task6a.FibonacciLinearAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.math.BigInteger;

@ShellComponent
@RequiredArgsConstructor
public class FibonacciCommand {
    private final FibonacciLinearAlgorithm linearAlgorithm;
    private final FibonacciForkJoinAlgorithm forkJoinAlgorithm;

    @ShellMethod("Fibonacci sequence by linear algorithm")
    public BigInteger fibonacci(int number) {
        return linearAlgorithm.apply(number);
    }

    @ShellMethod("Fibonacci sequence by ForkJoin algorithm")
    public BigInteger fibonacciTask(@ShellOption(defaultValue = "20") int number) {
        return forkJoinAlgorithm.apply(number);
    }
}
