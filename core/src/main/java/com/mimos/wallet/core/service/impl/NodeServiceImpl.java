package com.mimos.wallet.core.service.impl;

import com.mimos.wallet.base.enums.ChainSybmol;
import com.mimos.wallet.core.service.EthNodeService;
import com.mimos.wallet.core.service.NodeService;

import javax.annotation.Resource;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/3/8 2:43 PM
 */
public class NodeServiceImpl implements NodeService {

    @Resource
    EthNodeService ethNodeService;

    @Override
    public void buildTransafctionReq(int chainId, String reqJson) {

        ChainSybmol chainSybmol = ChainSybmol.getByValue(chainId);

        switch (chainSybmol) {
            case BTC:
                break;
            case ETH:
                ethNodeService.buildTransafctionReq(chainId,reqJson);
                break;
            case EOS:
                break;
        }
    }
}
