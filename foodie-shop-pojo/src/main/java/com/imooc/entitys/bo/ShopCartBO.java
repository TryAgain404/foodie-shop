package com.imooc.entitys.bo;

import lombok.Data;

/**
 * @author TryAgain404
 * @date 2020-11-5 17:04
 */
@Data
public class ShopCartBO {

    private String itemId;
    private String itemImgUrl;
    private String itemName;
    private String specId;
    private String specName;
    private Integer buyCounts;
    private String priceDiscount;
    private String priceNormal;
}
