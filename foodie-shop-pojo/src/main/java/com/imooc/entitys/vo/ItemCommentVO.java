package com.imooc.entitys.vo;

import lombok.Data;

import java.util.Date;

/**
 * 用于展示商品评价的VO
 *
 * @author TryAgain404
 * @date 2020-11-4 16:29
 */
@Data
public class ItemCommentVO {

    private Integer commentLevel;
    private String content;
    private String specName;
    private Date createdTime;
    private String userFace;
    private String nickname;
}
