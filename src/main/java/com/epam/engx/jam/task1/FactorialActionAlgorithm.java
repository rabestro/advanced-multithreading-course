package com.epam.engx.jam.task1;

import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;


@Setter
@Component
public class FactorialActionAlgorithm extends AbstractFactorialAlgorithm {
    private static final int DEFAULT_THRESHOLD = 10;
    private int threshold = DEFAULT_THRESHOLD;

    @Override
    BigInteger factorialFromTwo(int number) {
        var recursiveAction = new FactorialAction(1, number);
        var pool = new ForkJoinPool();
        pool.invoke(recursiveAction);
        return recursiveAction.getResult();
    }

    private final class FactorialAction extends RecursiveAction {
        private final int start;
        private final int end;

        private BigInteger result = BigInteger.ONE;

        public FactorialAction(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= threshold) {
                result = AbstractFactorialAlgorithm.computeDirectly(start, end);
                return;
            }
            var mid = start + (end - start) / 2;
            var left = new FactorialAction(start, mid);
            var right = new FactorialAction(mid + 1, end);

            invokeAll(left, right);
            result = left.getResult().multiply(right.getResult());
        }

        BigInteger getResult() {
            return result;
        }
    }
}
