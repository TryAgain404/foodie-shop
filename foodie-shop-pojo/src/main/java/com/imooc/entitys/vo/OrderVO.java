package com.imooc.entitys.vo;

import lombok.Data;

/**
 * @author TryAgain404
 * @date 2020-11-6 14:19
 */
@Data
public class OrderVO {

    private String orderId;
    private MerchantOrdersVO merchantOrdersVO;
}
