package com.mimos.wallet.service.impl;

import com.mimos.wallet.core.dto.RawEthTransactionDto;
import com.mimos.wallet.dal.common.generated.Tables;
import com.mimos.wallet.service.TransactionLocalService;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/3/9 12:00 PM
 */
@Service
public class TransactionLocalServiceImpl implements TransactionLocalService {

    @Resource
    DSLContext context;

    @Override
    public void inflateNoce(RawEthTransactionDto ethRaw) {
        if (ethRaw.getNonce() == null){
            Record1<Integer> nonceRecord = context.select(Tables.CHAIN_TRANSACTION_LOCAL.NONCE).from(Tables.CHAIN_TRANSACTION_LOCAL)
                    .where(Tables.CHAIN_TRANSACTION_LOCAL.STATUS.eq(1))
                    .and(Tables.CHAIN_TRANSACTION_LOCAL.FROM.eq(ethRaw.getFrom()))
                    .orderBy(Tables.CHAIN_TRANSACTION_LOCAL.NONCE.desc())
                    .limit(1)
                    .fetchOne();
            if (nonceRecord!=null){
                /**
                 * 存在 noce + 1
                 */
                ethRaw.setNonce(new BigInteger(nonceRecord.get(0)+"").add(BigInteger.ONE));
            }else {
                ethRaw.setNonce(BigInteger.ZERO);
            }
        }
    }
}
