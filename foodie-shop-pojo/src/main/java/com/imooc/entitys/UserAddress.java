package com.imooc.entitys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户地址表 
 *
 * @author TryAgain404
 * @email TryAgain500@163.com
 * @date 2020-10-21 11:25:18
 */
@Data
@TableName("user_address")
public class UserAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 地址主键id
	 */
	@TableId
	private String id;
	/**
	 * 关联用户id
	 */
	private String userId;
	/**
	 * 收件人姓名
	 */
	private String receiver;
	/**
	 * 收件人手机号
	 */
	private String mobile;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 区县
	 */
	private String district;
	/**
	 * 详细地址
	 */
	private String detail;
	/**
	 * 扩展字段
	 */
	private String extand;
	/**
	 * 是否默认地址 1:是  0:否
	 */
	private Integer isDefault;
	/**
	 * 创建时间
	 */
	private Date createdTime;
	/**
	 * 更新时间
	 */
	private Date updatedTime;

}
