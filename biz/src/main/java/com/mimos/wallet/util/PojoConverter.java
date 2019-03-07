package com.mimos.wallet.util;

import com.mimos.grpc.api.*;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainAddress;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainSummary;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainWalletPath;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainWalletRoot;
import com.mimos.wallet.dal.common.generated.tables.records.ChainBalcanceRecord;
import com.mimos.wallet.ext.DateTimeWithZone;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/2/28 2:12 PM
 */
public class PojoConverter {

    public static ChainWalletRoot chainWalletRoot2Pojo(CreateWalletRequest request){

        ChainWalletRoot chainWalletRoot = new ChainWalletRoot();
        chainWalletRoot.setRootPubkey(request.getRootPubKey());
        chainWalletRoot.setProtocol(request.getProtocolValue());

        DateTimeWithZone now = DateTimeWithZone.now();
        chainWalletRoot.setCreateTime(now.getTimeMillis());
        chainWalletRoot.setCreateZone(now.getZone());
        return chainWalletRoot;
    }

    public static WalletRoot chainWalletRoot2Msg(ChainWalletRoot chainWalletRoot){

       return WalletRoot.newBuilder()
               .setProtocolValue(chainWalletRoot.getProtocol())
               .setRootPubKey(chainWalletRoot.getRootPubkey())
               .setId(chainWalletRoot.getId()).build();
    }

    public static ChainWalletPath chainWalletPath2Pojo(SavePathRequest request){
        ChainWalletPath chainWalletPath = new ChainWalletPath();
        chainWalletPath.setWalletPath(request.getPath().getPath());
        chainWalletPath.setChainId(Long.valueOf(request.getPath().getSymbol()));
        chainWalletPath.setAccountId(request.getPath().getRootId());

        DateTimeWithZone now = DateTimeWithZone.now();
        chainWalletPath.setCreateTime(now.getTimeMillis());
        chainWalletPath.setCreateZone(now.getZone());

        return  chainWalletPath;
    }

    public static Path chainWalletPath2Msg(ChainWalletPath chainWalletPath){
        return Path.newBuilder().setId(chainWalletPath.getId())
                .setPath(chainWalletPath.getWalletPath())
                .setSymbol(chainWalletPath.getChainId().toString())
                .setRootId(chainWalletPath.getAccountId())
                .build();
    }

    public static ChainAddress address2Pojo(Address address){
        ChainAddress chainAddress = new ChainAddress();

        chainAddress.setAddress(address.getAddress());
        chainAddress.setChainId(Integer.parseInt(address.getSymbol()));

        DateTimeWithZone now = DateTimeWithZone.now();
        chainAddress.setCreateTime(now.getTimeMillis());
        chainAddress.setCreateZone(now.getZone());

        return chainAddress;

    }

    public static Balance balance2Mes(ChainBalcanceRecord chainBalcanceRecord) {
        return Balance.newBuilder()
                .setBalance(chainBalcanceRecord.getBalanceAfter().toString())
                .setBlockHash(chainBalcanceRecord.getBlockHash())
                .setHeight(chainBalcanceRecord.getBlockNumber())
                .setTxid(chainBalcanceRecord.getTxHash())
                .setAddress(Address.newBuilder()
                        .setAddress(chainBalcanceRecord.getAddress())
                        .setContractId(chainBalcanceRecord.getContractId())
                        .setIsToken(chainBalcanceRecord.getIsToken())
                        .setSymbol(chainBalcanceRecord.getChainId().toString())
                        .build()
                ).build();
    }

    public static Chain summary2Msg(ChainSummary chainSummary) {
        return Chain.newBuilder().setFeeRate(chainSummary.getFeeRate().toString())
                .setHeight(chainSummary.getBlockNumber())
                .setSymbol(chainSummary.getChainId().toString())
                .build();
    }
}
