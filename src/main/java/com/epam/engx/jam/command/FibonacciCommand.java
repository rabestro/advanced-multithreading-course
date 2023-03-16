package com.epam.engx.jam.command;

import com.epam.engx.jam.printer.BigIntegerPrinter;
import com.epam.engx.jam.task6a.FibonacciForkJoinAlgorithm;
import com.epam.engx.jam.task6a.FibonacciLinearAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class FibonacciCommand {
    private final FibonacciLinearAlgorithm linearAlgorithm;
    private final FibonacciForkJoinAlgorithm forkJoinAlgorithm;
    private final BigIntegerPrinter printer;

    @ShellMethod("Fibonacci sequence by linear algorithm")
    public String fibonacci(int number) {
        return printer.print(linearAlgorithm.apply(number));
    }

    @ShellMethod("Fibonacci sequence by ForkJoin algorithm")
    public String fibonacciTask(@ShellOption(defaultValue = "20") int number) {
        return printer.print(forkJoinAlgorithm.apply(number));
    }
}
