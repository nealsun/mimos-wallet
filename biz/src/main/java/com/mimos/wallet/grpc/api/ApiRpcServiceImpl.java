package com.mimos.wallet.grpc.api;

import com.mimos.grpc.api.*;
import com.mimos.wallet.dal.common.generated.tables.daos.ChainAddressDao;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainAddress;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainSummary;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainWalletPath;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainWalletRoot;
import com.mimos.wallet.service.*;
import com.mimos.wallet.util.PojoConverter;
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
 * @date: 2019/2/26 3:19 PM
 */
@Slf4j
@GrpcService(ApiServiceGrpc.class)
public class ApiRpcServiceImpl extends ApiServiceGrpc.ApiServiceImplBase {

    @Resource
    WalletRootService walletRootService;

    @Resource
    WalletPathService walletPathService;

    @Resource
    BalanceService balanceService;

    @Resource
    TransactionActionService transactionActionService;

    @Resource
    ChainAddressDao chainAddressDao;

    @Resource
    ChainSummaryService chainSummaryService;


    @Override
    public void createWallet(CreateWalletRequest request, StreamObserver<CommonResponse> responseObserver) {
        try {
            ChainWalletRoot chainWalletRoot = PojoConverter.chainWalletRoot2Pojo(request);
            walletRootService.onReprot(chainWalletRoot);

            WalletRoot walletRoot = PojoConverter.chainWalletRoot2Msg(chainWalletRoot);

            responseObserver.onNext(ResponseBuilder.sucApiResponse(walletRoot));
            responseObserver.onCompleted();
        }catch (Exception e){
            responseObserver.onError(e);
            log.error("queryWallet",e);
        }
    }

    @Override
    public void savePath(SavePathRequest request, StreamObserver<CommonResponse> responseObserver) {
      try {

          ChainWalletPath chainWalletPath = PojoConverter.chainWalletPath2Pojo(request);
          /** 保存 Path */
          walletPathService.onReprot(chainWalletPath);
          List<ChainAddress> addresses = request.getPath().getAddressesList().stream()
                  .map(PojoConverter::address2Pojo)
                  .map(addree -> setPathId(addree, chainWalletPath.getId()))
                  .collect(Collectors.toList());
          /** 保存地址 */
          if (addresses != null && addresses.size()>0) {
              chainAddressDao.insert(addresses);
          }
          /** 返回结果 */
          Path path = PojoConverter.chainWalletPath2Msg(chainWalletPath);
          responseObserver.onNext(ResponseBuilder.sucApiResponse(path));
          responseObserver.onCompleted();
      }catch (Exception e){
          responseObserver.onError(e);
          log.error("queryWallet",e);
      }
    }

    /**
     * 为 address 设置 Path ID
     * @param chainAddress
     * @param pathId
     * @return
     */
    private ChainAddress setPathId(ChainAddress chainAddress,Long pathId){
        if (chainAddress != null) {
            chainAddress.setPathId(pathId);
        }
        return chainAddress;
    }


    @Override
    public void queryWallet(QueryWalletRequest request, StreamObserver<CommonResponse> responseObserver) {
       try {
           String rootPublicKey = request.getRootPublicKey();

           List<Path> collect = walletPathService.getPathByRootPubkey(rootPublicKey).stream().map(PojoConverter::chainWalletPath2Msg).collect(Collectors.toList());

           /** 返回结果 */
           WalletTree walletTree = WalletTree.newBuilder().addAllPathes(collect).build();
           responseObserver.onNext(ResponseBuilder.sucApiResponse(walletTree));
           responseObserver.onCompleted();
       }catch (Exception e){
           responseObserver.onError(e);
           log.error("queryWallet",e);
       }
    }

    @Override
    public void queryHistory(AddressHistoryRequest request, StreamObserver<CommonResponse> responseObserver) {

        try {
            List<String> collect = request.getAddressesList().stream().map(Address::getAddress).collect(Collectors.toList());

            List<Transaction> transactions = transactionActionService.getListByAddress(collect, request.getPageIndex(), request.getPageSize());

            TransactionList transactionList = TransactionList.newBuilder().addAllTransactions(transactions).build();
            /** 返回结果 */
            responseObserver.onNext(ResponseBuilder.sucApiResponse(transactionList));
            responseObserver.onCompleted();
        }catch (Exception e){
            responseObserver.onError(e);
            log.error("queryHistory",e);
        }
    }

    @Override
    public void queryBalance(BalanceRequest request, StreamObserver<CommonResponse> responseObserver) {
        try {
            List<Balance> collect = request.getAddressesList().stream().map(address ->
                    balanceService.queryBalanceByAddressAnddChainId(address.getAddress(), Integer.parseInt(address.getSymbol()), address.getIsToken(), address.getContractId())
            ).map(PojoConverter::balance2Mes).collect(Collectors.toList());

            BalanceList balanceResp = BalanceList.newBuilder().addAllBalances(collect).build();
            /** 返回结果 */
            responseObserver.onNext(ResponseBuilder.sucApiResponse(balanceResp));
            responseObserver.onCompleted();
        }catch (Exception e){
            responseObserver.onError(e);
            log.error("queryHistory",e);
        }
    }

    @Override
    public void queryFeeRate(FeeRateRequest request, StreamObserver<CommonResponse> responseObserver) {
        //same with queryChainSummary();
    }

    @Override
    public void queryChainSummary(ChainSummaryRequest request, StreamObserver<CommonResponse> responseObserver) {
        try {
            ChainSummary chainSummary = chainSummaryService.getSummaryRecordByChainId(Long.valueOf(request.getSymbol()));

            /** 返回结果 */
            responseObserver.onNext(ResponseBuilder.sucApiResponse( PojoConverter.summary2Msg(chainSummary)));
            responseObserver.onCompleted();
        }catch (Exception e){
            responseObserver.onError(e);
            log.error("queryHistory",e);
        }
    }

    @Override
    public void queryUtxo(UtxoRequest request, StreamObserver<CommonResponse> responseObserver) {

    }
}
