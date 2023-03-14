package com.epam.engx.jam.command;

import com.epam.engx.jam.task6b.DirectLinearAlgorithm;
import com.epam.engx.jam.task6b.RecursiveActionAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.concurrent.ThreadLocalRandom;

@ShellComponent
@RequiredArgsConstructor
public class SumOfSquaresCommand {
    private static final double BOUND = 1000;
    private final DirectLinearAlgorithm linearAlgorithm;
    private final RecursiveActionAlgorithm actionAlgorithm;

    private double[] array;

    @ShellMethod("Prepare array filled by random doubles")
    public String prepareDoubles(@ShellOption(defaultValue = "500000000") int size) {
        array = ThreadLocalRandom.current()
            .doubles(0, BOUND)
            .limit(size)
            .toArray();
        return "Array with %,d doubles created successfully".formatted(size);
    }

    @ShellMethod("Calculate sum of squares in double[] array by direct linear algorithm")
    @ShellMethodAvailability("arrayAvailabilityCheck")
    public double squares() {
        return linearAlgorithm.applyAsDouble(array);
    }

    @ShellMethod("Calculate sum of squares in double[] array by using RecursiveAction")
    @ShellMethodAvailability("arrayAvailabilityCheck")
    public double squaresAction() {
        return actionAlgorithm.applyAsDouble(array);
    }

    public Availability arrayAvailabilityCheck() {
        return array != null ? Availability.available() : Availability.unavailable("""
                an array of doubles is not prepared.
                Please use 'prepare-doubles' command to create and fill an array.
                """);
    }

}
