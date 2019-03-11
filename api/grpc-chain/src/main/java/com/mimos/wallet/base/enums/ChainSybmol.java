package com.mimos.wallet.base.enums;

import lombok.Getter;

/**
 * @description:  链类别信息
 * @auther: dingyp
 * @date: 2019/1/7 3:29 PM
 */
public enum ChainSybmol {

    BTC(1,"bitcoin"),
    ETH(2,"ethereum"),
    EOS(3,"eos");

    @Getter
    private int value;

    @Getter
    private String name;

    ChainSybmol(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static ChainSybmol getByValue(int value){
        switch (value) {
            case 1:return BTC;
            case 2:return ETH;
            case 3:return EOS;
            default: return BTC;
        }
    }

}
