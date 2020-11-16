package com.imooc.controller;

import com.imooc.entitys.bo.OrderBO;
import com.imooc.entitys.vo.MerchantOrdersVO;
import com.imooc.entitys.vo.OrderVO;
import com.imooc.service.OrdersService;
import com.imooc.utils.Constant;
import com.imooc.utils.R;
import com.imooc.utils.enums.PayMethod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author TryAgain404
 * @date 2020-11-6 11:32
 */
@RestController
@RequestMapping("/orders")
@Api(value = "订单相关接口", tags = {"订单相关接口"})
public class OrderController {

    static Logger logger = LoggerFactory.getLogger(OrderController.class);

    /**
     * 支付中心的调用地址
     */
    private String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";

    /**
     * 微信支付成功 -> 支付中心 -> 天天吃货平台
     *                          |-> 回调通知的url
     */
    private String payReturnUrl = "http://api.z.mukewang.com/foodie-dev-api/orders/notifyMerchantOrderPaid";

    final OrdersService orderService;
    final RestTemplate restTemplate;

    public OrderController(OrdersService orderService, RestTemplate restTemplate) {
        this.orderService = orderService;
        this.restTemplate = restTemplate;
    }

    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public R create(@RequestBody OrderBO orderBO, HttpServletRequest request, HttpServletResponse response) {

        if (!orderBO.getPayMethod().equals(PayMethod.WEIXIN.type)
                && !orderBO.getPayMethod().equals(PayMethod.ALIPAY.type)) {
            return R.error("支付方式不支持！");
        }
        OrderVO orderVO = orderService.createOrder(orderBO);
        String orderId = orderVO.getOrderId();

        /**
         * 1001
         * 2002 -> 用户购买
         * 3003 -> 用户购买
         * 4004
         */
        // TODO 整合redis之后，完善购物车中的已结算商品清除，并且同步到前端的cookie
        MerchantOrdersVO merchantOrdersVO = orderVO.getMerchantOrdersVO();
        merchantOrdersVO.setReturnUrl(payReturnUrl);

        // 为了方便测试购买，所以所有的支付金额都统一改为1分钱
        merchantOrdersVO.setAmount(1);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("imoocUserId","imooc");
        headers.add("password","imooc");

        HttpEntity<MerchantOrdersVO> entity =
                new HttpEntity<>(merchantOrdersVO, headers);

        ResponseEntity<R> responseEntity =
                restTemplate.postForEntity(paymentUrl,
                        entity,
                        R.class);
        R paymentResult = responseEntity.getBody();
        if (!paymentResult.getCode().equals(Constant.SUCCESS)) {
            logger.error("发送错误：{}", paymentResult.getMsg());
            return R.error("支付中心订单创建失败，请联系管理员！");
        }

        return R.success(orderId);
    }
}
