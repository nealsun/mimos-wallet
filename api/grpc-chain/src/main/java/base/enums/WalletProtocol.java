package base.enums;

import lombok.Getter;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/1/9 11:50 AM
 */
public enum WalletProtocol {
    BIP_44(1,"bip44");

    @Getter
    int value;

    @Getter
    String name;

    WalletProtocol(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static WalletProtocol forNumber(int value) {
        switch(value) {
            case 1:
                return BIP_44;
            default:
                return null;
        }
    }
}
