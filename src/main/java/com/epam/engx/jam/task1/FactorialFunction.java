package com.epam.engx.jam.task1;

import java.math.BigInteger;
import java.util.function.IntFunction;

@FunctionalInterface
public interface FactorialFunction extends IntFunction<BigInteger> {

    default void validate(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("the factorial is defined for non-negative numbers only!");
        }
    }
}
