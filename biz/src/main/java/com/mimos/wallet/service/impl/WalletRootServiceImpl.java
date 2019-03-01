package com.mimos.wallet.service.impl;

import com.mimos.wallet.dal.common.generated.tables.daos.ChainWalletRootDao;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainWalletRoot;
import com.mimos.wallet.service.WalletRootService;
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
public class WalletRootServiceImpl implements WalletRootService {

    @Resource
    ChainWalletRootDao walletRootDao;


    @Override
    public void onReprot(ChainWalletRoot item) {
        walletRootDao.insert(item);
    }

    @Override
    public void onReportList(List<ChainWalletRoot> items) {
        walletRootDao.insert(items);
    }
}
