package com.mimos.wallet;

import com.mimos.wallet.dal.common.generated.Tables;
import com.mimos.wallet.dal.common.generated.tables.daos.ChainBalcanceDao;
import com.mimos.wallet.dal.common.generated.tables.records.ChainBalcanceRecord;
import com.mimos.wallet.ext.DateTimeWithZone;
import org.jooq.DSLContext;
import org.jooq.Table;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/2/26 10:36 AM
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BalanceInsertTest {

    @Resource
    ChainBalcanceDao chainBalcanceDao;

    @Resource
    DSLContext dslContext;

    @Test
    public void testInsert(){
        ChainBalcanceRecord balcanceRecord = dslContext.newRecord(Tables.CHAIN_BALCANCE);

        balcanceRecord.setId(11L);


        balcanceRecord.setTxActionId(11L);
        balcanceRecord.setTxHash("txHash-test");
        balcanceRecord.setBlockHash("txHash-test");
        balcanceRecord.setObsoleted(true);

        balcanceRecord.setBlockNumber(1L);

        DateTimeWithZone now = DateTimeWithZone.now();

        balcanceRecord.setCreateTime(now.getDatetime());
        balcanceRecord.setCreateZone(now.getZone());

        balcanceRecord.setBalanceBefor(BigInteger.ZERO);
        balcanceRecord.setBalanceAfter(BigInteger.TEN);

        balcanceRecord.setAddress("address");
        balcanceRecord.setChainId(1L);
        balcanceRecord.setIsToken(false);

        int store = balcanceRecord.store();

        System.out.println("stored ID = " + store);
    }
}
