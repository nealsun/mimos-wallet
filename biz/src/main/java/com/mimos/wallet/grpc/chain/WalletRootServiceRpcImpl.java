package com.mimos.wallet.grpc.chain;

import com.mimos.wallet.base.rpc.response.BaseGrpcResponse;
import com.mimos.wallet.base.rpc.wallet_root.WalletRootGreeterGrpc;
import com.mimos.wallet.base.rpc.wallet_root.WalletRootReq;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainWalletRoot;
import com.mimos.wallet.ext.DateTimeWithZone;
import com.mimos.wallet.service.WalletRootService;
import com.mimos.wallet.util.ReportServiceExecuter;
import com.mimos.wallet.util.ResponseBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;

import javax.annotation.Resource;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/1/18 3:15 PM
 */
@Slf4j
@GrpcService(WalletRootGreeterGrpc.class)
public class WalletRootServiceRpcImpl extends WalletRootGreeterGrpc.WalletRootGreeterImplBase {

    @Resource
    WalletRootService walletRootService;

    /**
     * RPC 实现
     * @param request
     * @param responseObserver
     */
    @Override
    public void report(WalletRootReq request, StreamObserver<BaseGrpcResponse> responseObserver) {

        ReportServiceExecuter.excute(walletRootService,reqToDTO(request),responseObserver,log);
    }

    /**
     *  转换
     * @param request
     * @return
     */
    private ChainWalletRoot reqToDTO(WalletRootReq request){

        ChainWalletRoot dto = new ChainWalletRoot();

        dto.setProtocol(request.getProtocol());
        dto.setRootPubkey(request.getRootPubKey());

        DateTimeWithZone now = DateTimeWithZone.now();

        dto.setCreateTime(now.getTimeMillis());
        dto.setCreateZone(now.getZone());

        return dto;
    }
}
