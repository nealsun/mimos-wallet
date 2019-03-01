package com.mimos.wallet.service.impl;


import com.mimos.wallet.dal.common.generated.Tables;
import com.mimos.wallet.dal.common.generated.tables.daos.ChainBalcanceDao;
import com.mimos.wallet.dal.common.generated.tables.daos.ChainSummaryDao;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainBalcance;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainTransactionAction;
import com.mimos.wallet.dal.common.generated.tables.records.ChainBalcanceRecord;
import com.mimos.wallet.ext.DateTimeWithZone;
import com.mimos.wallet.service.BalanceService;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

import static com.mimos.wallet.dal.common.generated.Tables.CHAIN_BALCANCE;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/1/28 2:23 PM
 */
@Component
@Profile("jooq")
public class BalanceServiceImpl implements BalanceService {

    @Resource
    private DSLContext context;

    @Resource
    ChainBalcanceDao chainBalcanceDao;

    @Override
    public void onTransactonActionDiscovery(ChainTransactionAction action) {

        /** 设置查询条件 */
        Condition condition = CHAIN_BALCANCE.CHAIN_ID.eq(action.getChainId())
                        .and(CHAIN_BALCANCE.ADDRESS.eq(action.getAddress()));
        /** 添加 token 查询条件 */
        if (action.getIsToken()) {
            condition.and(CHAIN_BALCANCE.CONTRACT_ID.eq(action.getContractId()))
                    .and(CHAIN_BALCANCE.IS_TOKEN.eq(true));
        }
        /** 查询 */
        Record1<BigInteger> balanceBefor = context.select(CHAIN_BALCANCE.BALANCE_AFTER).from(CHAIN_BALCANCE)
                .where(condition)
                .orderBy(Tables.CHAIN_BALCANCE.CREATE_TIME.desc())
                .limit(1)
                .fetchOne();
        /** 构建 存储对象 */
        ChainBalcance balcanceRecord = action2Record(action);
        /**  设置 beforeBalance */
        /** 判断 beforeBalcane > 0  */
        if (balanceBefor!=null &&  balanceBefor.get(CHAIN_BALCANCE.BALANCE_AFTER).compareTo(BigInteger.ZERO)!=0){ // !=0 可能为 负数
            BigInteger beforeBalance = balanceBefor.get(CHAIN_BALCANCE.BALANCE_AFTER);
            /** 设置 更改前金额 */
            balcanceRecord.setBalanceBefor(beforeBalance);
            /** 计算当前金额  */
            if (action.getIsIncome()) {
                balcanceRecord.setBalanceAfter( beforeBalance.add(action.getAmount()));
            }else {
                balcanceRecord.setBalanceAfter( beforeBalance.subtract(action.getAmount()));
            }
        }

        chainBalcanceDao.insert(balcanceRecord);
    }

    @Override
    public void onTransactonActionDiscovery(List<ChainTransactionAction> actions) {
        actions.stream().forEach(this::onTransactonActionDiscovery);
    }

    @Override
    public ChainBalcanceRecord queryBalanceByAddressAnddChainId(String address, long chainId,boolean isToken,String contractId) {
        return  context.selectFrom(Tables.CHAIN_BALCANCE)
                .where(Tables.CHAIN_BALCANCE.OBSOLETED.eq(false))
                .and(Tables.CHAIN_BALCANCE.IS_TOKEN.eq(isToken))
                .and(Tables.CHAIN_BALCANCE.CONTRACT_ID.eq(isToken?contractId:""))
                .and(Tables.CHAIN_BALCANCE.CHAIN_ID.eq(chainId))
                .and(Tables.CHAIN_BALCANCE.ADDRESS.eq(address))
                .orderBy(Tables.CHAIN_BALCANCE.BLOCK_NUMBER.desc(), Tables.CHAIN_BALCANCE.CREATE_TIME)
                .limit(0, 1)
                .fetchOne();

    }

    private ChainBalcance action2Record(ChainTransactionAction action){

        ChainBalcance balcanceRecord = new ChainBalcance();

        balcanceRecord.setTxActionId(action.getId());
        balcanceRecord.setTxHash(action.getTxHash());
        balcanceRecord.setBlockHash(action.getBlockHash());
        balcanceRecord.setBlockNumber(action.getBlockNumber());

        balcanceRecord.setBalanceBefor(BigInteger.ZERO);
        balcanceRecord.setBalanceAfter(action.getAmount());

        balcanceRecord.setAddress(action.getAddress());
        balcanceRecord.setChainId(action.getChainId());
        if (action.getIsToken()){
            balcanceRecord.setContractId(action.getContractId());
            balcanceRecord.setIsToken(true);
        }else {
            balcanceRecord.setIsToken(false);
            balcanceRecord.setContractId("");
        }

        DateTimeWithZone now = DateTimeWithZone.now();
        balcanceRecord.setCreateTime(now.getDatetime());
        balcanceRecord.setCreateZone(now.getZone());
        return balcanceRecord;
    }
}
