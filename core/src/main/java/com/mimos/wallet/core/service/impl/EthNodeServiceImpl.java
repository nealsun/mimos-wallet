package com.mimos.wallet.core.service.impl;

import com.mimos.wallet.core.grpc.EthRpcCliant;
import com.mimos.wallet.core.grpc.NodeClian;
import com.mimos.wallet.core.service.EthNodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/3/8 11:45 AM
 */
@Slf4j
@Service
public class EthNodeServiceImpl extends NodeServiceAdapter implements EthNodeService{

    @Resource
    EthRpcCliant ethRpcCliant;

    @Override
    NodeClian getNodeCliant() {
        return ethRpcCliant;
    }
}
