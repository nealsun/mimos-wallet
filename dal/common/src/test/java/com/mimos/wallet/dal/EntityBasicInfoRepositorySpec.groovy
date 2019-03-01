package com.mimos.wallet.dal

import com.mimos.wallet.dal.common.generated.tables.pojos.EntityBasicInfo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Issue
import spock.lang.Specification

class EntityBasicInfoRepositorySpec extends Specification {
    static Logger log = LoggerFactory.getLogger(EntityBasicInfoRepositorySpec.class)

    @Issue("test constraint")
    void test() {
        given:

        expect:
        true == true
    }
}
