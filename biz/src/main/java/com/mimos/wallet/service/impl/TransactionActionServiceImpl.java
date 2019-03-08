package com.mimos.wallet.service.impl;

import com.mimos.grpc.api.Address;
import com.mimos.grpc.api.Transaction;
import com.mimos.wallet.base.enums.ChainSybmol;
import com.mimos.wallet.dal.common.generated.Tables;
import com.mimos.wallet.dal.common.generated.tables.daos.ChainTransactionActionDao;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainTransactionAction;
import com.mimos.wallet.dal.common.generated.tables.records.ChainTransactionActionRecord;
import com.mimos.wallet.service.BalanceService;
import com.mimos.wallet.service.TransactionActionService;
import lombok.extern.slf4j.Slf4j;
import org.jooq.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/1/18 3:15 PM
 */
@Slf4j
@Service
public class TransactionActionServiceImpl  implements TransactionActionService {

    @Resource
    ChainTransactionActionDao chainTransactionActionDao;

    @Resource
    BalanceService balanceService;

    @Resource
    DSLContext context;

    @Override
    public void onReprot(com.mimos.wallet.dal.common.generated.tables.pojos.ChainTransactionAction item) {

        chainTransactionActionDao.insert(item);
        /**
         * 记录balance
         */
        balanceService.onTransactonActionDiscovery(item);
    }

    @Override
    public void onReportList(List<ChainTransactionAction> items) {

        chainTransactionActionDao.insert(items);
        /**
         * 记录balance
         */
        balanceService.onTransactonActionDiscovery(items);
    }

    @Override
    public List<Transaction> getListByAddress(List<Address> addresses,int pageIndex,int pageSize){

        if (addresses==null || addresses.size()==0 || pageSize ==0){
            return null;
        }

        SelectOnConditionStep<Record8<String, Long, Boolean, BigInteger, String, String, String, Long>> stepA = context.select(
                Tables.CHAIN_TRANSACTION_ACTION.BLOCK_HASH,
                Tables.CHAIN_TRANSACTION_ACTION.BLOCK_NUMBER,
                Tables.CHAIN_TRANSACTION_ACTION.IS_INCOME,
                Tables.CHAIN_TRANSACTION_ACTION.AMOUNT,
                Tables.CHAIN_TRANSACTION_ACTION.ADDRESS,
                Tables.CHAIN_TRANSACTION_ACTION.TX_HASH,
                Tables.CHAIN_TRANSACTION.FEE,
                Tables.CHAIN_TRANSACTION.TX_TIME
        )
                .from(Tables.CHAIN_TRANSACTION_ACTION)
                .leftJoin(Tables.CHAIN_TRANSACTION)
                .on(Tables.CHAIN_TRANSACTION.TX_HASH.eq(Tables.CHAIN_TRANSACTION_ACTION.TX_HASH));

        SelectConditionStep<Record8<String, Long, Boolean, BigInteger, String, String, String, Long>> stepB;

        Address address = addresses.get(0);
        if (addresses.size()==1){
            stepB = stepA
                    .where(Tables.CHAIN_TRANSACTION_ACTION.ADDRESS.eq(address.getAddress()))
                    .and(Tables.CHAIN_TRANSACTION_ACTION.IS_TOKEN.eq(address.getIsToken()));
                    if (address.getIsToken()){
                        stepB.and(Tables.CHAIN_TRANSACTION_ACTION.CONTRACT_ID.eq(address.getContractId()));
                    }
        }else {
            stepB = stepA
                    .where(Tables.CHAIN_TRANSACTION_ACTION.ADDRESS.in(addresses));
        }

        List<Record8<String, Long, Boolean, BigInteger, String, String, String, Long>> collects = new ArrayList<>(stepB.and(Tables.CHAIN_TRANSACTION_ACTION.CHAIN_ID.eq(Long.parseLong(address.getSymbol())))
                .and(Tables.CHAIN_TRANSACTION_ACTION.OBSOLETED.eq(false))
                .orderBy(Tables.CHAIN_TRANSACTION_ACTION.BLOCK_NUMBER.desc(), Tables.CHAIN_TRANSACTION_ACTION.CREATE_TIME.desc())
                .limit(pageIndex * pageSize, pageSize)
                .fetch());

        /**
         * BTC 计算 过滤 同一个交易中 地址找零 - fee
         */
        if (address.getSymbol().equals(ChainSybmol.BTC.getValue())) {
            Map<String, Record8<String, Long, Boolean, BigInteger, String, String, String, Long>> map = new HashMap<>();

            for (Record8<String, Long, Boolean, BigInteger, String, String, String, Long> collect : collects) {
                String key = String.format("%s-%s",collect.get( Tables.CHAIN_TRANSACTION_ACTION.TX_HASH),collect.get(Tables.CHAIN_TRANSACTION_ACTION.ADDRESS));

                BigInteger beforAmount = BigInteger.ZERO;
                BigInteger currentAmount = collect.get(Tables.CHAIN_TRANSACTION_ACTION.AMOUNT);

                if (map.containsKey(key)) {
                    Record8<String, Long, Boolean, BigInteger, String, String, String, Long> before = map.get(key);
                    beforAmount = before.get(Tables.CHAIN_TRANSACTION_ACTION.AMOUNT);
                }else {
                    Long fee = Long.valueOf(collect.get(Tables.CHAIN_TRANSACTION.FEE));
                    currentAmount = currentAmount.subtract(BigInteger.valueOf(fee));
                }

                if (collect.get(Tables.CHAIN_TRANSACTION_ACTION.IS_INCOME)) {
                    currentAmount = beforAmount.subtract(currentAmount);
                } else {
                    currentAmount = beforAmount.add(currentAmount);
                }
                collect.set(Tables.CHAIN_TRANSACTION_ACTION.AMOUNT ,currentAmount);
                map.put(key,collect);
            }
            collects = new ArrayList<>(map.values()) ;
        }
        /***
         * 组装结果
         */
        List<Transaction> collect = collects
                .stream()
                .map(record ->
                        Transaction.newBuilder()
                                .setSymbol(address.getSymbol())
                                .setBlockHash(record.get(Tables.CHAIN_TRANSACTION_ACTION.BLOCK_HASH))
                                .setHeight( record.get(Tables.CHAIN_TRANSACTION_ACTION.BLOCK_NUMBER))
                                .setIsIncome(record.get(Tables.CHAIN_TRANSACTION_ACTION.IS_INCOME))
                                .setAmount(record.get(Tables.CHAIN_TRANSACTION_ACTION.AMOUNT).toString())
                                .setFee(record.get(Tables.CHAIN_TRANSACTION.FEE))
                                .setTxid(record.get(Tables.CHAIN_TRANSACTION_ACTION.TX_HASH))
                                .setTime(record.get(Tables.CHAIN_TRANSACTION.TX_TIME)).build()
                )
                .collect(Collectors.toList());
        return  collect;
    }
}
