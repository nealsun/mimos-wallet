package base.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/1/7 3:15 PM
 */
@Getter
@Setter
public class WalletPathDTO extends BaseDTO {

    //主键id
    private Long id;

    /**
     * 所属 用户/ROOT ID
     * FK - {@link WalletRootDTO#id}
     */
    private Long accountId;

    /**
     *  (bip-44) 路径
     */
    private String walletPath;

    /**
     *  公钥 - 生产地址
     */
    private String pubKey;

    /**
     * 链ID {@link ChainSummaryDTO#id}
     */
    private long chainId;
    /**
     * 创建时间
     */
    private Timestamp createTime;
}
