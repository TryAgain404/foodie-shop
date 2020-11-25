package com.imooc.utils.enums;

import lombok.Data;

/**
 * @author TryAgain404
 * @Email TryAgain500@163.com
 * @create 2020-11-14 10:00
 **/
public enum  PayMethod {
    WEIXIN(1, "微信"),
    ALIPAY(2, "支付宝");

    public final Integer type;
    public final String value;

    PayMethod(Integer type, String value){
        this.type = type;
        this.value = value;
    }
}
