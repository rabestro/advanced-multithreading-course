package com.epam.engx.jam.task1;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.stream.IntStream;

@Component
public final class FactorialParallelStream extends AbstractFactorialAlgorithm {

    @Override
    BigInteger factorialFromTwo(int number) {
        return IntStream
            .iterate(2, i -> i <= number, i -> ++i)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
