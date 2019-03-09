package com.mimos.wallet.core.service;

import com.mimos.grpc.api.TransactionResponseData;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/3/7 5:01 PM
 */
public interface BtcNodeService{

    TransactionResponseData buildTransafctionReq(int chainId, String reqJson);

    int sendSignedRaw(int chainId,long reqeustId,String txHash, String data);
}
