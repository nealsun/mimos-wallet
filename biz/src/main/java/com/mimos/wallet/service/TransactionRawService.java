package com.mimos.wallet.service;


import com.mimos.wallet.dal.common.generated.tables.pojos.ChainTransactionRaw;

/**
 * @description: 交易签名数据上链
 * @auther: dingyp
 * @date: 2019/1/9 12:28 PM
 */
public interface TransactionRawService {

    void updateTrasnactionRaw(ChainTransactionRaw raw);
}
