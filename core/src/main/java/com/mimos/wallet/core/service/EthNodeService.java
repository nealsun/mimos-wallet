package com.mimos.wallet.core.service;

import com.mimos.grpc.api.TransactionResponseData;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/3/7 5:01 PM
 */
public interface EthNodeService {

    TransactionResponseData buildTransafctionReq(int chainId, String reqJson);
}
