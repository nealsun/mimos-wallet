package com.mimos.wallet.service;


import com.mimos.wallet.dal.common.generated.tables.daos.ChainSummaryDao;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainSummary;

import javax.annotation.Resource;

/**
 * @description: 链端信息 管理 - 上传最新 Height，FeeRate
 * @auther: dingyp
 * @date: 2019/1/9 12:13 PM
 */
public interface ChainSummaryService extends BaseReportService<ChainSummary> {


    ChainSummary getSummaryRecordByChainId(Long chainId);
}
