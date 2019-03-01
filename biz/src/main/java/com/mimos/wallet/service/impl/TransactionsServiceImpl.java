package com.mimos.wallet.service.impl;

import com.mimos.wallet.dal.common.generated.Tables;
import com.mimos.wallet.dal.common.generated.tables.daos.ChainBlockForkedRecoredDao;
import com.mimos.wallet.dal.common.generated.tables.daos.ChainTransactionDao;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainBlockForkedRecored;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainTransaction;
import com.mimos.wallet.ext.DateTimeWithZone;
import com.mimos.wallet.service.TransactionsService;
import lombok.extern.slf4j.Slf4j;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/1/18 3:15 PM
 */
@Slf4j
@Service
public class TransactionsServiceImpl implements TransactionsService {

    @Resource
    ChainTransactionDao transactionsDao;

    @Resource
    ChainBlockForkedRecoredDao chainBlockForkedRecoredDao;


    @Resource
    private DSLContext context;


    @Override
    public long blockObsoleted(Long blockNumber, String blockHash, Long tokenId) {

        ChainBlockForkedRecored blockForkedRecord = new ChainBlockForkedRecored();
        blockForkedRecord.setBlockNumber(blockNumber);
        blockForkedRecord.setBlockHash(blockHash);
        blockForkedRecord.setChainId(tokenId);

        DateTimeWithZone now = DateTimeWithZone.now();
        blockForkedRecord.setCreateTime(now.getTimeMillis());
        blockForkedRecord.setCreateZone(now.getZone());

        chainBlockForkedRecoredDao.insert(blockForkedRecord);
        /**
         * 标记 balance obsoleted
         */
        Table<Record> tableBalance = DSL.table(Tables.CHAIN_BALCANCE.getName());

        UpdateQuery<Record> updateQueryBalance = context.updateQuery(tableBalance);//获取更新对象
        updateQueryBalance.addValue(Tables.CHAIN_BALCANCE.OBSOLETED, true);//更新email字段的值为new-email
        Condition eqBalance = DSL.field(Tables.CHAIN_BALCANCE.BLOCK_HASH).eq(blockHash)
                .and(DSL.field(Tables.CHAIN_BALCANCE.CHAIN_ID).eq(tokenId))
                .and(DSL.field(Tables.CHAIN_BALCANCE.UPDATE_TIME).eq(now.getDatetime()))
                .and(DSL.field(Tables.CHAIN_BALCANCE.UPDATE_ZONE).eq(now.getZone()));
        updateQueryBalance.addConditions(eqBalance);
        int effectBalance = updateQueryBalance.execute();
        log.info("block-forked effectTransacton",effectBalance);
        /**
         * 标记 Transaction obsoleted
         */
        Table<Record> tableTransaction = DSL.table(Tables.CHAIN_TRANSACTION.getName());

        UpdateQuery<Record> updateQueryTransaction = context.updateQuery(tableTransaction);//获取更新对象
        updateQueryBalance.addValue(Tables.CHAIN_BALCANCE.OBSOLETED, true);//更新email字段的值为new-email
        Condition eqTransaction = DSL.field(Tables.CHAIN_BALCANCE.BLOCK_HASH).eq(blockHash)
                .and(DSL.field(Tables.CHAIN_BALCANCE.CHAIN_ID).eq(tokenId))
                .and(DSL.field(Tables.CHAIN_BALCANCE.UPDATE_TIME).eq(now.getDatetime()))
                .and(DSL.field(Tables.CHAIN_BALCANCE.UPDATE_ZONE).eq(now.getZone()));
        updateQueryTransaction.addConditions(eqTransaction);
        int effectTransacton =  updateQueryTransaction.execute();

        log.info("block-forked effectTransacton",effectTransacton);

        blockForkedRecord.setStatus(1);
        chainBlockForkedRecoredDao.update(blockForkedRecord);
        return blockForkedRecord.getId();
    }

    @Override
    public void onReprot(ChainTransaction item) {
        transactionsDao.insert(item);
    }

    @Override
    public void onReportList(List<ChainTransaction> items) {
        transactionsDao.insert(items);
    }
}
