package com.epam.engx.jam.task6b

import spock.lang.Rollup
import spock.lang.Specification

class DirectLinearAlgorithmTest extends Specification {

    @Rollup
    def 'calculate sum of squares in double[] array'(double[] array) {
        given:
        def algorithm = new DirectLinearAlgorithm()

        expect:
        algorithm.applyAsDouble(array) == sumOfSquares

        where:
        array              | sumOfSquares
        []                 | 0
        [0]                | 0
        [1]                | 1
        [-1]               | 1
        [2]                | 4
        [-5, 3, 2]         | 38
        [9, -7, 0, 29, 11] | 1092
    }
}
