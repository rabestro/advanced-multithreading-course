package com.epam.engx.jam.task1;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public final class FactorialLinearAlgorithm extends AbstractFactorialAlgorithm {

    @Override
    public BigInteger factorialFromTwo(int number) {
        return AbstractFactorialAlgorithm.computeDirectly(2, number);
    }
}
