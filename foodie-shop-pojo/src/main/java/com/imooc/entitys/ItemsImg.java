package com.imooc.entitys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品图片 
 *
 * @author TryAgain404
 * @email TryAgain500@163.com
 * @date 2020-10-21 11:25:18
 */
@Data
@TableName("items_img")
public class ItemsImg implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 图片主键
	 */
	@TableId
	private String id;
	/**
	 * 商品外键id 商品外键id
	 */
	private String itemId;
	/**
	 * 图片地址 图片地址
	 */
	private String url;
	/**
	 * 顺序 图片顺序，从小到大
	 */
	private Integer sort;
	/**
	 * 是否主图 是否主图，1：是，0：否
	 */
	private Integer isMain;
	/**
	 * 创建时间
	 */
	private Date createdTime;
	/**
	 * 更新时间
	 */
	private Date updatedTime;

}
