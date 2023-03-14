package com.epam.engx.jam.task6b;

import org.springframework.stereotype.Component;

import java.util.concurrent.ForkJoinPool;

@Component
public final class RecursiveActionAlgorithm implements SumOfSquaresFunction {

    @Override
    public double applyAsDouble(double[] array) {
        var pool = new ForkJoinPool();
        var applyer = new Applyer(array);
        pool.invoke(applyer);
        return applyer.result;
    }
}
