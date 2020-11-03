package com.imooc.entitys.vo;

import lombok.Data;

/**
 * @author TryAgain404
 * @date 2020-11-3 17:13
 */
@Data
public class SubCategoryVO {

    private Integer subId;
    private String subName;
    private String subType;
    private Integer subFatherId;
}
