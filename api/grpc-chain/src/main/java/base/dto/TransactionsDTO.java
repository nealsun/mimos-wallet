package base.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @description:交易流水
 * @auther: dingyp
 * @date: 2019/1/7 3:57 PM
 */
@Setter
@Getter
public class TransactionsDTO extends BaseDTO{

    //主键id
    private Long id;

    /**
     * 交易所属链
     * FK {@link TokenDTO#id}
     */
    private Long tokenId;

    private String txHash;

    private int txIndex;

    private Long height;

    private String blockHash;

    /**
     * 是否废弃
     */
    private boolean obsoleted;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新
     */
    private Timestamp updateTime;

}
