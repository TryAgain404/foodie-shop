package com.imooc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.entitys.Orders;
import com.imooc.entitys.bo.OrderBO;
import com.imooc.entitys.vo.OrderVO;

/**
 * 订单表
 *
 * @author TryAgain404
 * @email TryAgain500@163.com
 * @date 2020-10-21 11:33:24
 */
public interface OrdersService extends IService<Orders> {

    OrderVO createOrder(OrderBO orderBO);
}

