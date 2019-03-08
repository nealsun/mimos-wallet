package com.mimos.wallet.core.service;

import com.mimos.grpc.api.TransactionResponseData;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/3/8 2:41 PM
 */
public interface NodeService {

    TransactionResponseData buildTransafctionReq(int chainId, String reqJson);
}
