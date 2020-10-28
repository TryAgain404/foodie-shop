package com.imooc.mapper;

import com.imooc.entitys.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单表 
 *
 * @author TryAgain404
 * @email TryAgain500@163.com
 * @date 2020-10-21 11:33:24
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}
