package com.imooc.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.imooc.utils.validator.group.AddGroup;
import com.imooc.utils.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户表 
 *
 * @author TryAgain404
 * @email TryAgain500@163.com
 * @date 2020-10-21 11:25:18
 */
@TableName("users")
@Data
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键id 用户id
	 */
	@TableId(value = "id",type = IdType.INPUT)
	private String id;
	/**
	 * 用户名 用户名
	 */
	private String username;
	/**
	 * 密码 密码
	 */
	private String password;
	/**
	 * 昵称 昵称
	 */
	private String nickname;
	/**
	 * 真实姓名 真实姓名
	 */
	private String realname;
	/**
	 * 头像 头像
	 */
	private String face;
	/**
	 * 手机号 手机号
	 */
	@NotBlank(message = "手机不能为空", groups = {AddGroup.class})
	private String mobile;
	/**
	 * 邮箱地址 邮箱地址
	 */
	private String email;
	/**
	 * 性别 性别 1:男  0:女  2:保密
	 */
	private Integer sex;
	/**
	 * 生日 生日
	 */
	private Date birthday;
	/**
	 * 创建时间 创建时间
	 */
	private Date createdTime;
	/**
	 * 更新时间 更新时间
	 */
	private Date updatedTime;
	/**
	 * 删除标注
	 */
	private String delFlag;

	private String status;

}
