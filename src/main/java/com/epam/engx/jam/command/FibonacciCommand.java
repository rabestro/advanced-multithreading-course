package com.epam.engx.jam.command;

import com.epam.engx.jam.task6a.FibonacciAlgorithm;
import com.epam.engx.jam.task6a.FibonacciForkJoinAlgorithm;
import com.epam.engx.jam.task6a.FibonacciLinearAlgorithm;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@ShellComponent
public record FibonacciCommand(FibonacciLinearAlgorithm linearAlgorithm) {

    @ShellMethod("Fibonacci sequence by linear algorithm")
    public String fib(int number) {
        return calculate(linearAlgorithm, number);
    }

    @ShellMethod("Fibonacci sequence by ForkJoin algorithm")
    public String fibFj(int number, @ShellOption(defaultValue = "10") int granularity) {
        return calculate(new FibonacciForkJoinAlgorithm(granularity), number);
    }

    private String calculate(FibonacciAlgorithm algorithm, int number) {
        var now = ZonedDateTime.now();
        return """
            Number: %,d
            Result: %,d
            %s exec time: %,dms
            """.formatted(number, algorithm.apply(number),
            algorithm.getClass().getSimpleName(),
            ChronoUnit.MILLIS.between(now, ZonedDateTime.now()));
    }
}
