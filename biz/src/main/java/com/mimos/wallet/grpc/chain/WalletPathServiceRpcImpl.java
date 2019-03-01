package com.mimos.wallet.grpc.chain;

import com.mimos.wallet.base.rpc.response.BaseGrpcResponse;
import com.mimos.wallet.base.rpc.wallet_path.WalletPathGreeterGrpc;
import com.mimos.wallet.base.rpc.wallet_path.WalletPathListReq;
import com.mimos.wallet.base.rpc.wallet_path.WalletPathReq;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainWalletPath;
import com.mimos.wallet.ext.DateTimeWithZone;
import com.mimos.wallet.service.WalletPathService;
import com.mimos.wallet.util.ReportServiceExecuter;
import com.mimos.wallet.util.ResponseBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/1/18 3:15 PM
 */
@Slf4j
@GrpcService(WalletPathGreeterGrpc.class)
public class WalletPathServiceRpcImpl extends WalletPathGreeterGrpc.WalletPathGreeterImplBase {

    @Resource
    WalletPathService walletPathService;

    @Override
    public void reportList(WalletPathListReq request, StreamObserver<BaseGrpcResponse> responseObserver) {

        List<ChainWalletPath> collect = request.getItemsList().stream().map(this::reqToDTO).collect(Collectors.toList());

        ReportServiceExecuter.excute(walletPathService,collect,responseObserver,log);
    }

    /**
     * RPC 实现
     * @param request
     * @param responseObserver
     */
    @Override
    public void report(WalletPathReq request, StreamObserver<BaseGrpcResponse> responseObserver) {

        ReportServiceExecuter.excute(walletPathService,reqToDTO(request),responseObserver,log);
    }

    /**
     *  转换
     * @param request
     * @return
     */
    private ChainWalletPath reqToDTO(WalletPathReq request){

        ChainWalletPath dto = new ChainWalletPath();

        dto.setAccountId(request.getAccountId());
        dto.setChainId(request.getChainId());
        dto.setPubKey(request.getPubKey());
        dto.setWalletPath(request.getWalletPath());

        DateTimeWithZone now = DateTimeWithZone.now();

        dto.setCreateTime(now.getTimeMillis());
        dto.setCreateZone(now.getZone());
        dto.setUpdateTime(now.getTimeMillis());
        dto.setUpdateZone(now.getZone());

        return dto;
    }

}
