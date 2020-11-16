package com.imooc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.entitys.*;
import com.imooc.entitys.bo.OrderBO;
import com.imooc.entitys.vo.MerchantOrdersVO;
import com.imooc.entitys.vo.OrderVO;
import com.imooc.mapper.OrdersMapper;
import com.imooc.service.*;
import com.imooc.utils.enums.OrderStatusEnum;
import com.imooc.utils.enums.YesOrNo;
import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @author TryAgain404
 */
@Service("ordersService")
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    final
    Sid sid;
    final
    UserAddressService userAddressService;
    final
    ItemsSpecService itemsSpecService;
    final
    ItemsService itemsService;
    final
    ItemsImgService itemsImgService;
    final
    OrderItemsService orderItemsServicel;
    final
    OrderStatusService orderStatusService;

    public OrdersServiceImpl(Sid sid, UserAddressService userAddressService, ItemsSpecService itemsSpecService, ItemsService itemsService, ItemsImgService itemsImgService, OrderItemsService orderItemsServicel, OrderStatusService orderStatusService) {
        this.sid = sid;
        this.userAddressService = userAddressService;
        this.itemsSpecService = itemsSpecService;
        this.itemsService = itemsService;
        this.itemsImgService = itemsImgService;
        this.orderItemsServicel = orderItemsServicel;
        this.orderStatusService = orderStatusService;
    }

    @Override
    public OrderVO createOrder(OrderBO orderBO) {
        String userId = orderBO.getUserId();
        String addressId = orderBO.getAddressId();
        String itemSpecIds = orderBO.getItemSpecIds();
        Integer payMethod = orderBO.getPayMethod();
        String leftMsg = orderBO.getLeftMsg();
        // 包邮费用设置为0
        int postAmount = 0;
        String orderId = sid.nextShort();
        UserAddress address = userAddressService.getUserAddress(userId, addressId);
        // 1. 新订单数据保存
        Orders newOrder = new Orders();
        newOrder.setId(orderId);
        newOrder.setUserId(userId);
        newOrder.setReceiverName(address.getReceiver());
        newOrder.setReceiverMobile(address.getMobile());
        newOrder.setReceiverAddress(address.getProvince() + " " + address.getCity() + " " + address.getDistrict() + " "
                + address.getDetail());
        newOrder.setPostAmount(postAmount);
        newOrder.setPayMethod(payMethod);
        newOrder.setLeftMsg(leftMsg);
        newOrder.setIsComment(YesOrNo.no.type);
        newOrder.setIsDelete(YesOrNo.no.type);
        newOrder.setCreatedTime(new Date());
        newOrder.setUpdatedTime(new Date());

        // 2. 循环根据itemSpecIds保存订单商品信息表
        String[] itemSpecIdArr = itemSpecIds.split(",");
        // 商品原价累计
        int totalAmount = 0;
        // 优惠后的实际支付价格累计
        int realPayAmount = 0;
        for (String itemSpecId : itemSpecIdArr) {

            // TODO 整合redis后，商品购买的数量重新从redis的购物车中获取
            int buyCounts = 1;

            // 2.1 根据规格id，查询规格的具体信息，主要获取价格
            ItemsSpec itemSpec = itemsSpecService.queryItemSpecById(itemSpecId);
            totalAmount += itemSpec.getPriceNormal() * buyCounts;
            realPayAmount += itemSpec.getPriceDiscount() * buyCounts;

            // 2.2 根据商品id，获得商品信息以及商品图片
            String itemId = itemSpec.getItemId();
            Items item = itemsService.queryItemById(itemId);
            String imgUrl = itemsImgService.queryItemMainImgById(itemId);

            // 2.3 循环保存子订单数据到数据库
            String subOrderId = sid.nextShort();
            OrderItems subOrderItem = new OrderItems();
            subOrderItem.setId(subOrderId);
            subOrderItem.setOrderId(orderId);
            subOrderItem.setItemId(itemId);
            subOrderItem.setItemName(item.getItemName());
            subOrderItem.setItemImg(imgUrl);
            subOrderItem.setBuyCounts(buyCounts);
            subOrderItem.setItemSpecId(itemSpecId);
            subOrderItem.setItemSpecName(itemSpec.getName());
            subOrderItem.setPrice(itemSpec.getPriceDiscount());
            orderItemsServicel.save(subOrderItem);
            // 2.4 在用户提交订单以后，规格表中需要扣除库存
            itemsSpecService.decreaseItemSpecStock(itemSpecId, buyCounts);
        }

        newOrder.setTotalAmount(totalAmount);
        newOrder.setRealPayAmount(realPayAmount);
        save(newOrder);

        // 3. 保存订单状态表
        OrderStatus waitPayOrderStatus = new OrderStatus();
        waitPayOrderStatus.setOrderId(orderId);
        waitPayOrderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        waitPayOrderStatus.setCreatedTime(new Date());
        orderStatusService.save(waitPayOrderStatus);

        // 4. 构建商户订单，用于传给支付中心
        MerchantOrdersVO merchantOrdersVO = new MerchantOrdersVO();
        merchantOrdersVO.setMerchantOrderId(orderId);
        merchantOrdersVO.setMerchantUserId(userId);
        merchantOrdersVO.setAmount(realPayAmount + postAmount);
        merchantOrdersVO.setPayMethod(payMethod);

        // 5. 构建自定义订单vo
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderId(orderId);
        orderVO.setMerchantOrdersVO(merchantOrdersVO);

        return orderVO;
    }
}
