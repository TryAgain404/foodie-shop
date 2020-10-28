package com.imooc.entitys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单商品关联表 
 *
 * @author TryAgain404
 * @email TryAgain500@163.com
 * @date 2020-10-21 11:25:18
 */
@Data
@TableName("order_items")
public class OrderItems implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private String id;
	/**
	 * 归属订单id
	 */
	private String orderId;
	/**
	 * 商品id
	 */
	private String itemId;
	/**
	 * 商品图片
	 */
	private String itemImg;
	/**
	 * 商品名称
	 */
	private String itemName;
	/**
	 * 规格id
	 */
	private String itemSpecId;
	/**
	 * 规格名称
	 */
	private String itemSpecName;
	/**
	 * 成交价格
	 */
	private Integer price;
	/**
	 * 购买数量
	 */
	private Integer buyCounts;

}
