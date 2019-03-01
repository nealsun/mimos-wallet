package com.mimos.wallet.grpc.chain;

import com.mimos.wallet.base.rpc.response.BaseGrpcResponse;
import com.mimos.wallet.base.rpc.transaction_action.TransactionActionGreeterGrpc;
import com.mimos.wallet.base.rpc.transaction_action.TransactionActionListReq;
import com.mimos.wallet.base.rpc.transaction_action.TransactionActionReq;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainTransactionAction;
import com.mimos.wallet.service.TransactionActionService;
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
@GrpcService(TransactionActionGreeterGrpc.class)
public class TransactionActionRpcServiceImpl extends TransactionActionGreeterGrpc.TransactionActionGreeterImplBase {

    @Resource
    TransactionActionService transactionActionService;

    @Override
    public void reportList(TransactionActionListReq request, StreamObserver<BaseGrpcResponse> responseObserver) {

        List<ChainTransactionAction> collect = request.getItemsList().stream().map(this::reqToDTO).collect(Collectors.toList());

        ReportServiceExecuter.excute(transactionActionService,collect,responseObserver,log);
    }

    /**
     * RPC 实现
     * @param request
     * @param responseObserver
     */
    @Override
    public void report(TransactionActionReq request, StreamObserver<BaseGrpcResponse> responseObserver) {

        ReportServiceExecuter.excute(transactionActionService,reqToDTO(request),responseObserver,log);
    }

    /**
     * DTO 转换
     * @param request
     * @return
     */
    private ChainTransactionAction reqToDTO(TransactionActionReq request){
        return RpcEntryParseTool.toTransactionAction(request);
    }
}
