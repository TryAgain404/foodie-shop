package com.imooc.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TryAgain404
 * @date 2020-11-6 11:32
 */
@RestController
@RequestMapping("/orders")
@Api(value = "订单相关接口", tags = {"订单相关接口"})
public class OrderController {
}
