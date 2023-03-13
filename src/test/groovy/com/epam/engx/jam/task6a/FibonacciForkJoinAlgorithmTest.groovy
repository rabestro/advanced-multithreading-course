package com.epam.engx.jam.task6a

import spock.lang.Specification
import spock.lang.Title
import spock.lang.Unroll

@Title('Fibonacci sequence using fork join framework')
class FibonacciForkJoinAlgorithmTest extends Specification {

    @Unroll('Fib(#index) = #fibonacci')
    def 'calculate fibonacci number using fork join framework'() {
        given:
        def granularitySize = 10
        def algorithm = new FibonacciForkJoinAlgorithm(granularitySize)

        expect:
        algorithm.apply(index) == fibonacci

        where:
        index | fibonacci
        0     | 0
        1     | 1
        2     | 1
        3     | 2
        4     | 3
        45    | 1134903170
    }
}
