package com.mimos.wallet.service.impl;

import com.mimos.wallet.dal.common.generated.tables.daos.ChainTransactionRawDao;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainTransactionRaw;
import com.mimos.wallet.grpc.chain.TransactionRawServiceRpcImpl;
import com.mimos.wallet.service.TransactionRawService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/2/25 10:36 AM
 */
@Component
public class TransactionRawServiceImpl implements TransactionRawService {

    @Resource
    ChainTransactionRawDao transactionRawDao;

    @Resource
    TransactionRawServiceRpcImpl transactionRawServiceRpc;

    @Override
    public void updateTrasnactionRaw(ChainTransactionRaw raw) {

        transactionRawServiceRpc.sendMsg(raw);

    }
}
