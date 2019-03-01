package com.mimos.wallet.core

import org.spockframework.spring.SpringSpy
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Issue
import spock.lang.Specification

@SpringBootTest
class AddServiceSpec extends Specification {
    @SpringSpy
    AddService addService

    @Issue("test basic add")
    void test() {
        given:
        def a = 5

        expect:
        addService.add(a, x) == result

        where:
        x | result
        1 | 6
        2 | 7
        5 | 10
    }
}
