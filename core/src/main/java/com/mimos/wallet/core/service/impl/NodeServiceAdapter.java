package com.mimos.wallet.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.mimos.grpc.api.TransactionResponseData;
import com.mimos.wallet.core.grpc.NodeClian;
import com.mimos.wallet.core.service.NodeService;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainTransactionLocal;
import com.mimos.wallet.dal.common.generated.tables.daos.ChainTransactionLocalDao;
import com.mimos.wallet.ext.DateTimeWithZone;
import com.mimos.wallet.grpc.*;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/3/8 2:44 PM
 */
public abstract class NodeServiceAdapter {

    abstract  NodeClian getNodeCliant();

    @Resource
    ChainTransactionLocalDao chainTransactionLocalDao;

    public TransactionResponseData buildRequest(int chainId, String reqJson) {

        TransactionRawData rawData = getNodeCliant().getStub().buildTransactionRaw(TransactionReqData.newBuilder().setChainId(chainId).setRequsetJson(reqJson).build());

        RequestAction requsetAction = rawData.getRequsetAction();

        ChainTransactionLocal transactionLocal = new ChainTransactionLocal();
        transactionLocal.setFrom(requsetAction.getFromAddrss());
        transactionLocal.setTo(requsetAction.getToAddress());
        transactionLocal.setAmount(new BigInteger(requsetAction.getAmount()));
        transactionLocal.setChainId(chainId);
        transactionLocal.setFee(Long.valueOf(requsetAction.getFee()));
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

        chainTransactionLocalDao.insert(transactionLocal);

        TransactionResponseData responseData = TransactionResponseData.newBuilder().setData(rawData.getData())
                .setChainId(chainId)
                .setReqId(transactionLocal.getId())
                .build();
        return responseData;

    }
    public int sendSignedRaw(int chainId, long reqeustId, String txHash, String data) {

        SignedRawRequest req = SignedRawRequest.newBuilder()
                .setChainId(chainId)
                .setRawData(txHash)
                .setRawData(data)
                .build();

        getNodeCliant().getStub().sendSignedraw(req);
        return 1;
    }

}
