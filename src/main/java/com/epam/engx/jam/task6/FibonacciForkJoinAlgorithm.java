package com.epam.engx.jam.task6;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

public final class FibonacciForkJoinAlgorithm implements FibonacciAlgorithm {
    private static final FibonacciAlgorithm linear = new FibonacciLinearAlgorithm();

    final int granularitySize;

    public FibonacciForkJoinAlgorithm(int n) {
        this.granularitySize = n;
    }

    @Override
    public BigInteger apply(int number) {
        return new FibonacciTask(number).compute();
    }

    final class FibonacciTask extends RecursiveTask<BigInteger> {
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
