package com.mimos.wallet.service;


import com.mimos.wallet.dal.common.generated.tables.pojos.ChainWalletPath;

import java.util.List;

/**
 * @description: 路径服务
 * @auther: dingyp
 * @date: 2019/1/9 12:07 PM
 */
public interface WalletPathService extends BaseReportService<ChainWalletPath>{
    List<ChainWalletPath> getPathByRootPubkey(String  rootPubkey);
}
