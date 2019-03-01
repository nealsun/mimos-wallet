package com.mimos.wallet.service;


import com.mimos.wallet.dal.common.generated.tables.pojos.ChainTransaction;

/**
 * @description: 交易处理
 * @auther: dingyp
 * @date: 2019/1/9 12:18 PM
 */
public interface TransactionsService extends BaseReportService<ChainTransaction> {

    long blockObsoleted(Long blockNumber, String blockHash, Long tokenId) throws Exception;

}
