package com.epam.engx.jam.task6;

import java.math.BigInteger;

public final class FibonacciLinearAlgorithm implements FibonacciAlgorithm {

    @Override
    public BigInteger apply(int number) {
        if (number <= 1) {
            return BigInteger.valueOf(number);
        }
        var a = BigInteger.ZERO;
        var b = BigInteger.ONE;
        while (number-- > 1) {
            var c = a.add(b);
            a = b;
            b = c;
        }
        return b;
    }
}
