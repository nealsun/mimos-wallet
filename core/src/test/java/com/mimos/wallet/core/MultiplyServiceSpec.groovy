package com.mimos.wallet.core

import spock.lang.Issue
import spock.lang.Specification
import org.spockframework.spring.SpringSpy
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MultiplyServiceSpec extends Specification {

    @SpringSpy
    MultiplyService multiplyService

    @Issue("test multiply")
    void test() {
        given:
        def a = 10

        expect:
        multiplyService.multiply(a, x) == result

        where:
        x | result
        2 | 8
        3 | 7
    }

    @Issue("test mock multiply")
    void testSpy() {
        given:
        multiplyService.multiply(a, b) >> a * b

        expect:
        multiplyService.multiply(a, b) == result
        multiplyService.multiplyTwo(c) == square

        where:
        a | b  | result | c | square
        2 | 10 | 20     | 2 | 6
        3 | 7  | 21     | 3 | 9
    }
}
