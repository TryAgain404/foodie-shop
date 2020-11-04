package com.imooc.entitys.vo;

import lombok.Data;

/**
 * 用于展示商品搜索列表结果的VO
 *
 * @author TryAgain404
 * @date 2020-11-4 22:28
 */
@Data
public class SearchItemsVO {

    private String itemId;
    private String itemName;
    private int sellCounts;
    private String imgUrl;
    private int price;
}
