package com.mimos.wallet.core.service.impl;

import com.mimos.grpc.api.TransactionResponseData;
import com.mimos.wallet.base.enums.ChainSybmol;
import com.mimos.wallet.core.service.BtcNodeService;
import com.mimos.wallet.core.service.EthNodeService;
import com.mimos.wallet.core.service.NodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/3/8 2:43 PM
 */
@Service
public class NodeServiceImpl implements NodeService {

    @Resource
    EthNodeService ethNodeService;

    @Resource
    BtcNodeService btcNodeService;

    @Override
    public TransactionResponseData buildTransafctionReq(int chainId, String reqJson) {

        ChainSybmol chainSybmol = ChainSybmol.getByValue(chainId);

        switch (chainSybmol) {
            case BTC:
                return btcNodeService.buildTransafctionReq(chainId,reqJson);
            case ETH:
                return ethNodeService.buildTransafctionReq(chainId,reqJson);
            case EOS:
                break;
        }
        return null;
    }

    @Override
    public int sendSignedRaw(int chainId, long reqeustId, String txHash, String data) {
        ChainSybmol chainSybmol = ChainSybmol.getByValue(chainId);

        switch (chainSybmol) {
            case BTC:
                return btcNodeService.sendSignedRaw(chainId,reqeustId,txHash,data);
            case ETH:
                return ethNodeService.sendSignedRaw(chainId,reqeustId,txHash,data);
            case EOS:
                break;
        }
        return 1;
    }


}
