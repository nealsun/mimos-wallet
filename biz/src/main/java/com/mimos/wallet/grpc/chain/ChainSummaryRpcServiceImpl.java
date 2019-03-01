package com.mimos.wallet.grpc.chain;

import com.mimos.wallet.base.rpc.chain_summary.ChainSummaryGreeterGrpc;
import com.mimos.wallet.base.rpc.chain_summary.ChainSummaryListReq;
import com.mimos.wallet.base.rpc.chain_summary.ChainSummaryReq;
import com.mimos.wallet.base.rpc.response.BaseGrpcResponse;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainSummary;
import com.mimos.wallet.ext.DateTimeWithZone;
import com.mimos.wallet.service.ChainSummaryService;
import com.mimos.wallet.util.ReportServiceExecuter;
import com.mimos.wallet.util.ResponseBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/1/18 3:15 PM
 */
@Slf4j
@GrpcService(ChainSummaryGreeterGrpc.class)
public class ChainSummaryRpcServiceImpl extends ChainSummaryGreeterGrpc.ChainSummaryGreeterImplBase{

    @Resource
    ChainSummaryService chainSummaryService;

    @Override
    public void reportList(ChainSummaryListReq request, StreamObserver<BaseGrpcResponse> responseObserver) {

        List<ChainSummary> collect = request.getItemsList().stream().map(this::reqToDTO).collect(Collectors.toList());

        ReportServiceExecuter.excute(chainSummaryService,collect,responseObserver,log);

    }

    /**
     * RPC 实现
     * @param request
     * @param responseObserver
     */
    @Override
    public void report(ChainSummaryReq request, StreamObserver<BaseGrpcResponse> responseObserver) {

        ReportServiceExecuter.excute(chainSummaryService,reqToDTO(request),responseObserver,log);
    }

    /**
     * DTO 转换
     * @return
     */
    private ChainSummary reqToDTO(ChainSummaryReq request){

        ChainSummary transactionsDTO = new ChainSummary();

        transactionsDTO.setFeeRate(request.getFeeRate());
        transactionsDTO.setBlockNumber(request.getHeight());
        transactionsDTO.setId(request.getId());
        transactionsDTO.setSymbol(request.getSymbol());
        transactionsDTO.setSymbolName(request.getSymbolName());

        DateTimeWithZone now = DateTimeWithZone.now();

        transactionsDTO.setCreateTime(now.getTimeMillis());
        transactionsDTO.setCreateZone(now.getZone());


        transactionsDTO.setUpdateTime(now.getTimeMillis());
        transactionsDTO.setUpdateZone(now.getZone());

        return transactionsDTO;
    }
}
