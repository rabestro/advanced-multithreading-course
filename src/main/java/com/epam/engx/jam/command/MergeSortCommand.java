package com.epam.engx.jam.command;

import com.epam.engx.jam.task2.MergeSort;
import com.epam.engx.jam.task2.impl.MergeSortImpl;
import com.epam.engx.jam.task2.impl.ParallelMergeSortImpl;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@ShellComponent
public class MergeSortCommand {
    private static final List<MergeSort> mergeSortImpls =
        List.of(new MergeSortImpl(), new ParallelMergeSortImpl());

    @ShellMethod("sort ints")
    public void sort(@ShellOption(defaultValue = "10000000") int size) {
        System.out.println("sort " + size);
        for (MergeSort mergeSort : mergeSortImpls) {
            int[] arr = IntStream
                .range(0, size)
                .map(i -> ThreadLocalRandom.current().nextInt())
                .toArray();

            var now = ZonedDateTime.now();
            mergeSort.accept(arr);
            System.out.printf("%s exec time: %dms%n",
                mergeSort.getClass().getSimpleName(),
                ChronoUnit.MILLIS.between(now, ZonedDateTime.now()));
        }

    }
}
