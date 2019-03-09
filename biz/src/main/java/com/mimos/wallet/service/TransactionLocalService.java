package com.mimos.wallet.service;


import com.mimos.wallet.core.dto.RawEthTransactionDto;

/**
 * @description: 交易明细处理
 * @auther: dingyp
 * @date: 2019/1/9 12:28 PM
 */
public interface TransactionLocalService {

    void inflateNoce(RawEthTransactionDto ethRaw);
}
