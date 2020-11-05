package com.imooc.entitys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.imooc.utils.validator.group.AddGroup;
import com.imooc.utils.validator.group.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
	@NotBlank(message = "用户ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String userId;
	/**
	 * 收件人姓名
	 */
	@Size(min = 1, max = 3, message = "收件人姓名长度不能为0或者大于5")
	@NotBlank(message = "收件人姓名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String receiver;
	/**
	 * 收件人手机号
	 */
	@NotBlank(message = "收件人手机号不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String mobile;
	/**
	 * 省份
	 */
	@NotBlank(message = "省份不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String province;
	/**
	 * 城市
	 */
	@NotBlank(message = "城市不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String city;
	/**
	 * 区县
	 */
	@NotBlank(message = "区县不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String district;
	/**
	 * 详细地址
	 */
	@NotBlank(message = "详细地址不能为空", groups = {AddGroup.class, UpdateGroup.class})
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
