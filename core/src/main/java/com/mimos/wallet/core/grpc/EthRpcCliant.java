package com.mimos.wallet.core.grpc;

import com.mimos.wallet.grpc.TransactionGreeterGrpc;
import com.mimos.wallet.grpc.TransactionReqData;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/3/7 5:36 PM
 */
@Component
public class EthRpcCliant {

    @GrpcClient("eth-node")
    private io.grpc.Channel serverChannel;

    public void buildTest(){

        TransactionReqData req = TransactionReqData.newBuilder().setChainId(1).build();
        TransactionGreeterGrpc.newBlockingStub(serverChannel).buildTransactionRaw(req);

    }

}
