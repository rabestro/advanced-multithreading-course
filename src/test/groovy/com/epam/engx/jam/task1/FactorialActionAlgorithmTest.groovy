package com.epam.engx.jam.task1

import spock.lang.Specification
import spock.lang.Unroll

class FactorialActionAlgorithmTest extends Specification {

    @Unroll('F(#number) = #factorial')
    def 'calculate factorial by linear algorithm'() {
        given:
        def algorithm = new FactorialActionAlgorithm()

        expect:
        algorithm.apply(number) == factorial

        where:
        number | factorial
        0      | 1
        1      | 1
        2      | 2
        3      | 6
        4      | 24
        9      | 362880
        28     | 304888344611713860501504000000
    }
}
