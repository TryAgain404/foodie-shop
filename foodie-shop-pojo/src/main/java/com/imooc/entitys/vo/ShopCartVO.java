package com.imooc.entitys.vo;

import lombok.Data;

/**
 * @author TryAgain404
 * @date 2020-11-5 17:04
 */
@Data
public class ShopCartVO {

    private String itemId;
    private String itemImgUrl;
    private String itemName;
    private String specId;
    private String specName;
    private String priceDiscount;
    private String priceNormal;
}
