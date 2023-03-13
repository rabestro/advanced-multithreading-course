package com.epam.engx.jam.task6b;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public final class DirectLinearAlgorithm implements SumOfSquaresFunction {

    @Override
    public double applyAsDouble(double[] array) {
        return Arrays.stream(array).map(x -> x * x).sum();
    }
}
