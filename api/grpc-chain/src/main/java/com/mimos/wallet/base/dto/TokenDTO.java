package com.mimos.wallet.base.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/1/7 3:46 PM
 */
@Getter
@Setter
public class TokenDTO extends BaseDTO {
    //主键id
    private Long id;

    /**
     * token name
     */
    private String symbol;

    /**
     * 精度 一般18位
     */
    private int percision;

    /**
     * 安全确认数
     */
    private int safeConfirmation;

    /**
     * 父链id
     * null 则为主链
     * 子链 则关联 {@link #id}
     */
    private Long  parentId;

    /**
     * 合约协议-ERO20等
     */
    private String contractProto;

    /**
     * 是否废弃（Token开关）
     */
    boolean obsoleted;

    /**
     * 合约地址
     */
    private String contractAddress;
}
