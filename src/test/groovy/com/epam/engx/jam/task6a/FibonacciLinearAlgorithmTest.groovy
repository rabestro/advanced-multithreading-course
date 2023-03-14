package com.epam.engx.jam.task6a


import spock.lang.Specification
import spock.lang.Title
import spock.lang.Unroll

@Title('Fibonacci sequence linear algorithm')
class FibonacciLinearAlgorithmTest extends Specification {

    @Unroll('Fib(#index) = #fibonacci')
    def 'calculate fibonacci number by linear algorithm'() {
        given:
        def algorithm = new FibonacciLinearAlgorithm()

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
        99    | 218922995834555169026
        120   | 5358359254990966640871840
    }
}
