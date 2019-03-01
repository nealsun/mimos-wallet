package com.mimos.wallet.service.impl;

import com.mimos.wallet.dal.common.generated.tables.daos.ChainSummaryDao;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainSummary;
import com.mimos.wallet.service.ChainSummaryService;
import lombok.extern.slf4j.Slf4j;
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
public class ChainSummaryServiceImpl implements ChainSummaryService {

    @Resource
    ChainSummaryDao chianSummaryDao;

    @Override
    public void onReprot(ChainSummary item) {
        chianSummaryDao.insert(item);
    }

    @Override
    public void onReportList(List<ChainSummary> items) {
        chianSummaryDao.insert(items);
    }

    @Override
    public ChainSummary getSummaryRecordByChainId(Long chainId){
        return chianSummaryDao.fetchByChainId(chainId).get(0);
    }

}
