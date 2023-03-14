package com.epam.engx.jam.task1;

import jakarta.validation.constraints.Min;

import java.math.BigInteger;
import java.util.stream.IntStream;

abstract class AbstractFactorialAlgorithm implements FactorialFunction {
    abstract BigInteger factorialFromTwo(int number);

    @Override
    public BigInteger apply(@Min(0) int value) {
        validate(value);
        if (value < 2) {
            return BigInteger.ONE;
        }
        return factorialFromTwo(value);
    }

    void validate(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("the factorial is defined only for non-negative numbers");
        }
    }

    static BigInteger computeDirectly(int start, int end) {
        return IntStream
            .iterate(start, i -> i <= end, i -> ++i)
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
