package com.mimos.wallet.core;
import com.mimos.wallet.ext.MayFail;

public interface BasicInfoService {
    MayFail<String> fetchNameById(Long id);
}
