package com.mimos.wallet.util;

import com.google.protobuf.Any;
import com.mimos.grpc.api.CommonResponse;
import com.mimos.wallet.base.enums.ResponseStatus;
import com.mimos.wallet.base.rpc.response.BaseGrpcResponse;
import io.grpc.binarylog.Message;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;


/**
 * @description:
 * @auther: dingyp
 * @date: 2019/1/21 5:17 PM
 */
@Slf4j
public class ResponseBuilder {

    public static void onSuccess(StreamObserver<BaseGrpcResponse> responseObserver){

        try {
            BaseGrpcResponse response = BaseGrpcResponse.newBuilder().setCode(ResponseStatus.SUCCESS.getCode())
                    .setMessage(ResponseStatus.SUCCESS.getMessage())
                    .build();

            responseObserver.onNext(response);

        }catch (Exception e){
            responseObserver.onError(e);
        }finally {
            responseObserver.onCompleted();
        }
    }

    public static <T extends  com.google.protobuf.Message> CommonResponse sucApiResponse(T t){
       return  CommonResponse.newBuilder()
                .setCode(ResponseStatus.SUCCESS.getCode())
                .setMessage(ResponseStatus.SUCCESS.getMessage())
                .setResult(Any.pack(t))
                .build();
    }

}
