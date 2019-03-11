package base.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/1/7 4:13 PM
 */
@Getter
@Setter
public class BalanceDTO extends BaseDTO {

    //主键id
    private Long id;

    /**
     * 流水明细ID
     * FK {@link TransactionActionDTO#id}
     */
    private Long txActionId;

    private String address;

    /**
     * FK {@link TokenDTO#id}
     */
    private String tokenId;

    private BigDecimal balanceBefor;

    private BigDecimal balanceAfter;

    private String blockHash;

    private long height;

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
