package com.epam.engx.jam.task1;

import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

@Setter
@Component
public class FactorialTaskAlgorithm extends AbstractFactorialAlgorithm {
    private static final int DEFAULT_THRESHOLD = 10;
    private int threshold = DEFAULT_THRESHOLD;

    @Override
    BigInteger factorialFromTwo(int number) {
        var recursiveTask = new FactorialRecursiveTask(1, number);
        var pool = new ForkJoinPool();
        return pool.invoke(recursiveTask);
    }

    private final class FactorialRecursiveTask extends RecursiveTask<BigInteger> {
        private final int start;
        private final int end;

        public FactorialRecursiveTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected BigInteger compute() {
            if (end - start <= threshold) {
                return AbstractFactorialAlgorithm.computeDirectly(start, end);
            }
            var mid = start + (end - start) / 2;

            var left = new FactorialRecursiveTask(start, mid);
            left.fork();
            var right = new FactorialRecursiveTask(mid + 1, end);
            return right.compute().multiply(left.join());
        }
    }
}
