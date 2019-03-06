package com.mimos.wallet.service;


import com.mimos.grpc.api.Address;
import com.mimos.grpc.api.Transaction;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainTransactionAction;

import java.util.List;

/**
 * @description: 交易明细处理
 * @auther: dingyp
 * @date: 2019/1/9 12:28 PM
 */
public interface TransactionActionService extends  BaseReportService<ChainTransactionAction> {

    List<Transaction> getListByAddress(List<Address> addresses, int pageIndex, int pageSize);
}
