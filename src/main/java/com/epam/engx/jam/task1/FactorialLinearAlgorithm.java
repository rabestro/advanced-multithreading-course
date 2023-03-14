package com.epam.engx.jam.task1;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.stream.IntStream;

@Component
public final class FactorialLinearAlgorithm extends AbstractFactorialAlgorithm {

    @Override
    public BigInteger factorialFromTwo(int number) {
        return IntStream
            .iterate(number, i -> i > 2, i -> --i)
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.TWO, BigInteger::multiply);
    }
}
