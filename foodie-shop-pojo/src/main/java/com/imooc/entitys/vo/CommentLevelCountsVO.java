package com.imooc.entitys.vo;

import lombok.Data;

/**
 * 用于展示商品评价数量的vo
 *
 * @author TryAgain404
 * @date 2020-11-4 16:33
 */
@Data
public class CommentLevelCountsVO {
    public Integer totalCounts;
    public Integer goodCounts;
    public Integer normalCounts;
    public Integer badCounts;
}
