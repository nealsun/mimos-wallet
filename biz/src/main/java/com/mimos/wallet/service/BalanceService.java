package com.mimos.wallet.service;


import com.mimos.wallet.dal.common.generated.tables.pojos.ChainTransactionAction;
import com.mimos.wallet.dal.common.generated.tables.records.ChainBalcanceRecord;

import java.util.List;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/1/28 2:20 PM
 */
public interface BalanceService {

    void onTransactonActionDiscovery(ChainTransactionAction action);

    void onTransactonActionDiscovery(List<ChainTransactionAction> actions);

    ChainBalcanceRecord queryBalanceByAddressAnddChainId(String address, long chainId, boolean isToken, String contractId);
}
