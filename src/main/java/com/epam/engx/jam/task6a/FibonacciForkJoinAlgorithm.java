package com.epam.engx.jam.task6a;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

public record FibonacciForkJoinAlgorithm(int granularitySize) implements FibonacciAlgorithm {
    private static final FibonacciAlgorithm linear = new FibonacciLinearAlgorithm();

    @Override
    public BigInteger apply(int number) {
        return new FibonacciTask(number).compute();
    }

    private final class FibonacciTask extends RecursiveTask<BigInteger> {
        final int n;

        FibonacciTask(int n) {
            this.n = n;
        }

        @Override
        protected BigInteger compute() {
            if (n <= granularitySize) {
                return linear.apply(n);
            }
            var f1 = new FibonacciTask(n - 1);
            f1.fork();
            var f2 = new FibonacciTask(n - 2);
            return f2.compute().add(f1.join());
        }
    }
}
