package com.mimos.wallet.core.impl;

import com.mimos.wallet.core.AddService;
import org.springframework.stereotype.Component;

@Component
public class AddServiceImpl implements AddService {
    @Override
    public Integer add(Integer a, Integer b) {
        return a + b;
    }
}
