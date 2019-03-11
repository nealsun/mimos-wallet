package base.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @description: 交易流水 明细
 * @auther: dingyp
 * @date: 2019/1/7 4:05 PM
 */
@Getter
@Setter
public class TransactionActionDTO extends BaseDTO {

    //主键id
    private Long id;

    /**
     * FK {@link TransactionsDTO#id}
     */
    private Long txId;

    /**
     * 交易Enent类型 transfer，CreateContract .etc
     */
    private int type;

    /**
     * 交易类型，入账/出账
     * {@link com.mimos.wallet.base.enums.TxType}
     */
    private  boolean direction;

    /**
     * 次序位置
     */
    private int index;

    private String blockHash;
    /**
     * 一条交易的 转入和转出 分为两条Action存在
     * 所以只有一个address
     */
    private String address;

    private BigDecimal amount;

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
