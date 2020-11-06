package com.imooc.entitys.vo;

import lombok.Data;

/**
 * @author TryAgain404
 * @date 2020-11-6 14:20
 */
@Data
public class MerchantOrdersVO {
    /**
     * 商户订单号
     */
    private String merchantOrderId;
    /**
     * 商户方的发起用户的用户主键id
     */
    private String merchantUserId;
    /**
     * 实际支付总金额（包含商户所支付的订单费邮费总额）
     */
    private Integer amount;
    /**
     * 支付方式 1:微信   2:支付宝
     */
    private Integer payMethod;
    private String returnUrl;
}
