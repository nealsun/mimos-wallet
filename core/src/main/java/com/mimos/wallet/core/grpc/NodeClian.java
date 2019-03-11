package com.mimos.wallet.core.grpc;

import com.mimos.wallet.grpc.TransactionGreeterGrpc;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/3/7 5:36 PM
 */
public abstract class NodeClian {

    public TransactionGreeterGrpc.TransactionGreeterBlockingStub getStub() {
        return  TransactionGreeterGrpc.newBlockingStub(getChannel());
    }

    public abstract io.grpc.Channel getChannel();

}
