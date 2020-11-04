package com.imooc.entitys.vo;

import lombok.Data;

import java.util.List;

/**
 * 最新商品VO
 *
 * @author TryAgain404
 * @date 2020-11-4 10:14
 */
@Data
public class NewItemsVO {
    private Integer rootCatId;
    private String rootCatName;
    private String slogan;
    private String catImage;
    private String bgColor;

    private List<SimpleItemVO> simpleItemList;
}
