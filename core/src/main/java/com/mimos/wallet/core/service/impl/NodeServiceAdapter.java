package com.mimos.wallet.core.service.impl;

import com.mimos.grpc.api.TransactionResponseData;
import com.mimos.wallet.core.grpc.NodeClian;
import com.mimos.wallet.core.service.NodeService;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainTransactionLocal;
import com.mimos.wallet.dal.common.generated.tables.daos.ChainTransactionLocalDao;
import com.mimos.wallet.ext.DateTimeWithZone;
import com.mimos.wallet.grpc.RequestAction;
import com.mimos.wallet.grpc.TransactionRawData;
import com.mimos.wallet.grpc.TransactionReqData;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/3/8 2:44 PM
 */
public abstract class NodeServiceAdapter  implements NodeService {

    abstract  NodeClian getNodeCliant();

    @Resource
    ChainTransactionLocalDao chainTransactionLocalDao;

    @Override
    public TransactionResponseData buildTransafctionReq(int chainId, String reqJson) {

        TransactionRawData rawData = getNodeCliant().getStub().buildTransactionRaw(TransactionReqData.newBuilder().setChainId(chainId).setRequsetJson(reqJson).build());

        RequestAction requsetAction = rawData.getRequsetAction();

        ChainTransactionLocal transactionLocal = new ChainTransactionLocal();
        transactionLocal.setFrom(requsetAction.getFromAddrss());
        transactionLocal.setTo(requsetAction.getToAddress());
        transactionLocal.setAmount(new BigInteger(requsetAction.getAmount()));
        transactionLocal.setChainId(chainId);
        String txHash = requsetAction.getTxHash();
        if (txHash != null) {
            transactionLocal.setTxHash(txHash);
        }else {
            transactionLocal.setTxHash("");
        }
        transactionLocal.setStatus(0);

        DateTimeWithZone now = DateTimeWithZone.now();
        transactionLocal.setCreateTime(now.getTimeMillis());
        transactionLocal.setCreateZone(now.getZone());

        chainTransactionLocalDao.insert();

        TransactionResponseData responseData = TransactionResponseData.newBuilder().setData(rawData.getData())
                .setChainId(chainId)
                .setReqId(transactionLocal.getId())
                .build();
        return responseData;

    }
}
