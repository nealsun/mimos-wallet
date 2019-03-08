package com.mimos.wallet.core.grpc;

import io.grpc.Channel;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/3/7 5:36 PM
 */
@Component
public class EthRpcCliant extends NodeClian {

    @GrpcClient("node-eth")
    private io.grpc.Channel serverChannel;

    @Override
    public Channel getChannel() {
        return serverChannel;
    }

}
