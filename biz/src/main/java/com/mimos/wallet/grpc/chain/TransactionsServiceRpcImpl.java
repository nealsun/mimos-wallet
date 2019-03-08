package com.mimos.wallet.grpc.chain;

import com.mimos.wallet.base.rpc.response.BaseGrpcResponse;
import com.mimos.wallet.base.rpc.transactions.*;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainTransaction;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainTransactionAction;
import com.mimos.wallet.ext.DateTimeWithZone;
import com.mimos.wallet.service.TransactionActionService;
import com.mimos.wallet.service.TransactionsService;
import com.mimos.wallet.util.ReportServiceExecuter;
import com.mimos.wallet.util.ResponseBuilder;
import com.mimos.wallet.util.RpcEntryParseTool;
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
@GrpcService(TransctionsGreeterGrpc.class)
public class TransactionsServiceRpcImpl extends TransctionsGreeterGrpc.TransctionsGreeterImplBase {

    @Resource
    TransactionsService transactionsService;

    @Resource
    TransactionActionService transactionActionService;

    @Override
    public void reportList(TransactionsListReq request, StreamObserver<BaseGrpcResponse> responseObserver) {

        List<ChainTransaction> collect = request.getItemsList().stream().map(this::reqToDTO).collect(Collectors.toList());

        ReportServiceExecuter.excute(transactionsService,collect,responseObserver,log);

    }

    @Override
    public void reportWithActionList(TransactionAndActionsReq request, StreamObserver<BaseGrpcResponse> responseObserver) {
        TransactionsReq transactionReq = request.getTransaction();

        try {
            transactionsService.onReprot(reqToDTO(transactionReq));

            List<ChainTransactionAction> collect = request.getActionsList().stream().map(RpcEntryParseTool::toTransactionAction).collect(Collectors.toList());

            transactionActionService.onReportList(collect);
            ResponseBuilder.onSuccess(responseObserver);
        } catch (Exception e) {
            // 保存失败
            responseObserver.onError(e);
            log.error("reportWithActionList",e);
        }

    }

    /**
     * RPC 实现
     * @param request
     * @param responseObserver
     */
    @Override
    public void report(TransactionsReq request, StreamObserver<BaseGrpcResponse> responseObserver) {

        ReportServiceExecuter.excute(transactionsService,reqToDTO(request),responseObserver,log);
    }

    /**
     * 处理分叉逻辑
     * @param request
     * @param responseObserver
     */
    @Override
    public void reportBlockObsoleted(BlockHasReq request, StreamObserver<BaseGrpcResponse> responseObserver) {

        try {
            transactionsService.blockObsoleted(request.getBlockNumber(), request.getBlockHash(), request.getTokenId());
            ResponseBuilder.onSuccess(responseObserver);
        } catch (Exception e) {
            responseObserver.onError(e);
            log.error("blockObsoleted",e);
        }
    }

    /**
     * DTO 转换
     * @param request
     * @return
     */
    private ChainTransaction reqToDTO(TransactionsReq request){

        ChainTransaction transactionsDTO = new ChainTransaction();

        transactionsDTO.setBlockHash(request.getBlockHash());
        transactionsDTO.setBlockNumber(request.getHeight());
        transactionsDTO.setChainId(request.getTokenId());
        transactionsDTO.setTxHash(request.getTxHash());
        transactionsDTO.setObsoleted(request.getObsoleted());
        transactionsDTO.setTxTime(request.getTxTime());

        DateTimeWithZone now = DateTimeWithZone.now();
        transactionsDTO.setCreateTime(now.getTimeMillis());
        transactionsDTO.setCreateZone(now.getZone());
        transactionsDTO.setFee(request.getFee());

        return transactionsDTO;
    }

}
