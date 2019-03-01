package com.mimos.wallet.service.impl;

import com.mimos.wallet.dal.common.generated.tables.daos.ChainWalletPathDao;
import com.mimos.wallet.dal.common.generated.tables.pojos.ChainWalletPath;
import com.mimos.wallet.service.WalletPathService;
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
public class WalletPathServiceImpl implements WalletPathService {

    @Resource
    ChainWalletPathDao walletPathDao;


    @Override
    public void onReprot(ChainWalletPath item) {
        walletPathDao.insert(item);
    }

    @Override
    public void onReportList(List<ChainWalletPath> items) {
        walletPathDao.insert(items);
    }

    @Override
    public List<ChainWalletPath> getPathByRootPubkey(String rootPubkey) {

        return  walletPathDao.fetchByPubKey(rootPubkey);
    }
}
