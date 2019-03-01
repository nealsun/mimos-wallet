package com.mimos.wallet.util;

import com.mimos.wallet.base.rpc.response.BaseGrpcResponse;
import com.mimos.wallet.service.BaseReportService;
import io.grpc.stub.StreamObserver;

import java.util.List;
import org.slf4j.Logger;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/2/26 6:02 PM
 */
public class ReportServiceExecuter<T> {
    public static  <T> void excute(BaseReportService reportService, T t, StreamObserver<BaseGrpcResponse> responseObserver, Logger logger){
        try {
            reportService.onReprot(t);
            ResponseBuilder.onSuccess(responseObserver);
        } catch (Exception e) {
            responseObserver.onError(e);
            logger.error("grpc.report",e);
        }
    }

    public static  <T> void excute(BaseReportService reportService, List<T> items, StreamObserver<BaseGrpcResponse> responseObserver, Logger logger){
        try {
            reportService.onReportList(items);
            ResponseBuilder.onSuccess(responseObserver);
        } catch (Exception e) {
            responseObserver.onError(e);
            logger.error("grpc.report",e);
        }
    }

}
