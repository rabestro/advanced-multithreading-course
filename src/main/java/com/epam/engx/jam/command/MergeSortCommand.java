package com.epam.engx.jam.command;

import com.epam.engx.jam.task2.impl.MergeSortImpl;
import com.epam.engx.jam.task2.impl.ParallelMergeSortImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.concurrent.ThreadLocalRandom;

@ShellComponent
@RequiredArgsConstructor
public class MergeSortCommand {
    private final MergeSortImpl linearAlgorithm;
    private final ParallelMergeSortImpl actionAlgorithm;

    private int[] array;

    @ShellMethod("Prepare array filled by random integers")
    public String prepareInts(@ShellOption(defaultValue = "50000000") int size) {
        array = ThreadLocalRandom.current().ints().limit(size).toArray();
        return "Array with %,d ints created successfully".formatted(size);
    }

    @ShellMethod("Sorts an array of integers by single thread algorithm")
    @ShellMethodAvailability("arrayAvailabilityCheck")
    public String sort() {
        linearAlgorithm.accept(array);
        return "The array with %,d elements sorted successfully".formatted(array.length);
    }

    @ShellMethod("Sorts an array of integers by using class RecursiveAction")
    @ShellMethodAvailability("arrayAvailabilityCheck")
    public String sortAction() {
        actionAlgorithm.accept(array);
        return "The array with %,d elements sorted successfully".formatted(array.length);
    }

    public Availability arrayAvailabilityCheck() {
        return array != null ? Availability.available() : Availability.unavailable("""
            an array of integers is not created and prepared.
            Please use 'prepare-ints' command to create and fill an array.
            """);
    }
}
