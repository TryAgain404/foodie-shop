package com.imooc.entitys.bo;

import lombok.Data;

/**
 * @author TryAgain404
 * @date 2020-11-6 14:22
 */
@Data
public class OrderBO {

    private String userId;
    private String itemSpecIds;
    private String addressId;
    private Integer payMethod;
    private String leftMsg;
}
