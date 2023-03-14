package com.epam.engx.jam.task1;

import java.math.BigInteger;

abstract class AbstractFactorialAlgorithm implements FactorialFunction {
    abstract BigInteger factorialFromTwo(int number);

    @Override
    public BigInteger apply(int value) {
        validate(value);
        if (value < 2) {
            return BigInteger.ONE;
        }
        return factorialFromTwo(value);
    }

}
