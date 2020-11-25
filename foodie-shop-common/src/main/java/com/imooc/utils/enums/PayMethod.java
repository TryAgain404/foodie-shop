package com.imooc.utils.enums;

/**
 * @author TryAgain404
 * @date 2020-11-16 9:52
 */
public enum PayMethod {
    /**
     * 支付方式
     */
    WEIXIN(1, "微信"),
    ALIPAY(2, "支付宝"),;

    public final Integer type;
    public final String value;

    PayMethod(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
