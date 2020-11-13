package com.imooc.mapper;

import com.imooc.entitys.ItemsSpec;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计
 *
 * @author TryAgain404
 * @email TryAgain500@163.com
 * @date 2020-10-21 11:33:24
 */
@Mapper
public interface ItemsSpecMapper extends BaseMapper<ItemsSpec> {
    /**
     * 库存
     * @param pendingCounts
     * @return
     */
    int decreaseItemSpecStock(@Param("pendingCounts") Integer pendingCounts);

}
