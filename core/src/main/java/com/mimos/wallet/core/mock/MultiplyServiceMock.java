package com.mimos.wallet.core.mock;

import com.mimos.wallet.core.MultiplyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("mock")
@Component
public class MultiplyServiceMock implements MultiplyService {
    @Override
    public Integer multiply(Integer a, Integer b) {
        return a - b;
    }

    @Override
    public Integer multiplyTwo(Integer a) {
        return a * 3;
    }
}
