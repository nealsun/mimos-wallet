package com.mimos.wallet.base.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


/**
 * @description: 链信息
 * @auther: dingyp
 * @date: 2019/1/7 3:24 PM
 */
@Getter
@Setter
public class ChainSummaryDTO extends BaseDTO {

    //主键id
    private Long id;

    /**
     * 链类别 (eth,btc,eos...)
     * {@link com.mimos.wallet.base.enums.ChainSybmol}
     */
    private int symbol;

    private String symbolName;

    /**
     * 高度
     */
    private long height;

    /**
     * 手续费 费率
     */
    private double feeRate;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新
     */
    private Timestamp updateTime;
}
