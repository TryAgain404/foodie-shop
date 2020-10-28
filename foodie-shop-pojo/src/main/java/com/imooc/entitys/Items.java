package com.imooc.entitys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表
 *
 * @author TryAgain404
 * @email TryAgain500@163.com
 * @date 2020-10-21 11:25:18
 */
@Data
@TableName("items")
public class Items implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品主键id
	 */
	@TableId
	private String id;
	/**
	 * 商品名称 商品名称
	 */
	private String itemName;
	/**
	 * 分类外键id 分类id
	 */
	private Integer catId;
	/**
	 * 一级分类外键id 一级分类id，用于优化查询
	 */
	private Integer rootCatId;
	/**
	 * 累计销售 累计销售
	 */
	private Integer sellCounts;
	/**
	 * 上下架状态 上下架状态,1:上架 2:下架
	 */
	private Integer onOffStatus;
	/**
	 * 商品内容 商品内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private Date createdTime;
	/**
	 * 更新时间
	 */
	private Date updatedTime;

}
