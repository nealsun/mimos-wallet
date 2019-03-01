package com.mimos.wallet.grpc.chain;

import com.mimos.wallet.base.rpc.chain_summary.ChainSummaryGreeterGrpc;
import com.mimos.wallet.base.rpc.transaction_raw.NodeConnnectResp;
import com.mimos.wallet.base.rpc.transaction_raw.TransactionRawGreeterGrpc;
import com.mimos.wallet.base.rpc.transaction_raw.TransactionRawSend;
import com.mimos.wallet.dal.common.generated.tables.daos.ChainTransactionRawDao;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainTransactionRaw;
import com.mimos.wallet.dal.common.generated.tables.records.ChainTransactionRawRecord;
import com.mimos.wallet.ext.DateTimeWithZone;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/1/18 3:15 PM
 */
@Slf4j
@GrpcService(ChainSummaryGreeterGrpc.class)
@Scope("prototype")
public class TransactionRawServiceRpcImpl extends TransactionRawGreeterGrpc.TransactionRawGreeterImplBase {



    @Resource
    ChainTransactionRawDao transactionRawDao;

    private StreamObserver<TransactionRawSend> responseObserver;

    @Override
    public StreamObserver<NodeConnnectResp> sendTransactionRaw(StreamObserver<TransactionRawSend> responseObserver) {

        this.responseObserver = responseObserver;

       return new StreamObserver<NodeConnnectResp>() {
           @Override
           public void onNext(NodeConnnectResp resp) {
               log.info("onNextsss",resp.toString());
//               TransactionRawSend send = TransactionRawSend.newBuilder().setId(System.currentTimeMillis()).setRaw("raw="+System.currentTimeMillis()).setTokenId("ETH").build();
//               responseObserver.onNext(send);

               ChainTransactionRawRecord raw = new ChainTransactionRawRecord();
               raw.setId(resp.getId());
               raw.setIsSuc(resp.getIsSuc());
               raw.setStatue(2);

               DateTimeWithZone now = DateTimeWithZone.now();
               raw.setUpdateTime(now.getTimeMillis());
               raw.setUpdateZone(now.getZone());

               raw.store();
           }

           @Override
           public void onError(Throwable t) {
               log.info("onError",t.getMessage());
               responseObserver.onError(t);
           }

           @Override
           public void onCompleted() {
               log.info("onCompleted");
               responseObserver.onCompleted();
           }
       };

    }

    public boolean sendMsg(ChainTransactionRaw raw){

        TransactionRawSend send =  TransactionRawSend.newBuilder()
                                    .setId(raw.getId())
                                    .setRaw(raw.getTxRaw())
                                    .setTokenId(raw.getChainId().toString())
                                    .build();
        try {
            responseObserver.onNext(send);
            raw.setStatue(1);
            transactionRawDao.insert(raw);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
