package com.imooc.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.entitys.OrderStatus;
import com.imooc.mapper.OrderStatusMapper;
import com.imooc.service.OrderStatusService;
import org.springframework.stereotype.Service;

@Service("orderStatusService")
public class OrderStatusServiceImpl extends ServiceImpl<OrderStatusMapper, OrderStatus> implements OrderStatusService {


}
