package com.epam.engx.jam.task6a;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

@Component
public final class FibonacciForkJoinAlgorithm implements FibonacciAlgorithm {
    private static final int THRESHOLD = 10;
    private static final FibonacciAlgorithm linear = new FibonacciLinearAlgorithm();

    @Override
    public BigInteger apply(int number) {
        return new FibonacciTask(number).compute();
    }

    private final class FibonacciTask extends RecursiveTask<BigInteger> {
        final int n;

        private FibonacciTask(int n) {
            this.n = n;
        }

        @Override
        protected BigInteger compute() {
            if (n <= THRESHOLD) {
                return linear.apply(n);
            }
            var f1 = new FibonacciTask(n - 1);
            f1.fork();
            var f2 = new FibonacciTask(n - 2);
            return f2.compute().add(f1.join());
        }
    }
}
