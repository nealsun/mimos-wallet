package com.mimos.wallet.util;

import com.mimos.wallet.base.rpc.transaction_action.TransactionActionReq;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainTransactionAction;
import com.mimos.wallet.ext.DateTimeWithZone;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/1/25 4:08 PM
 */
public class RpcEntryParseTool {

    /**
     * DTO 转换
     * @param request
     * @return
     */
    public static ChainTransactionAction toTransactionAction(TransactionActionReq request){

        ChainTransactionAction transactions = new ChainTransactionAction();

        transactions.setChainId(request.getTokenId());
        transactions.setTxHash(request.getTxHash());
        transactions.setBlockNumber(request.getBlockNumber());
        transactions.setBlockHash(request.getBlockHash());
        transactions.setAddress(request.getAddress());
        transactions.setIsToken(request.getIsToken());
        transactions.setContractId(request.getContractId());
        try {
            transactions.setAmount(new BigInteger(request.getAmount()));
        } catch (Exception e) {
            transactions.setAmount(BigInteger.ZERO);
            e.printStackTrace();
        }

        transactions.setActionIdex(request.getIndex());

        transactions.setType(request.getType());
        transactions.setIsIncome(request.getIsIncome());

        DateTimeWithZone now = DateTimeWithZone.now();
        transactions.setCreateTime(now.getTimeMillis());
        transactions.setCreateZone(now.getZone());

        return transactions;
    }

}
