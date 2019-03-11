package base.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @description: 钱包根节点
 * @auther: conanliu
 * @date: 18-1-7 13:19
 */
@Getter
@Setter
public class WalletRootDTO extends BaseDTO {
    //主键id
    private Long id;

    /**
     * 根pubKey
     */
    private String rootPubKey;
    /**
     * 协议： bip-44 等
     * {@link com.mimos.wallet.base.enums.WalletProtocol}
     */
    private int protocol;

    /**
     * 创建时间
     */
    private Timestamp createTime;

}
