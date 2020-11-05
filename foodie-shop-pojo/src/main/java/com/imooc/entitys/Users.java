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
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;

	public Users() {
	}

	public Users(String id, String username, String password, String nickname,
				 String realname, String face,
				 @NotBlank(message = "手机不能为空", groups = {AddGroup.class}) String mobile, String email, Integer sex,
				 Date birthday, Date createdTime, Date updatedTime) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.realname = realname;
		this.face = face;
		this.mobile = mobile;
		this.email = email;
		this.sex = sex;
		this.birthday = birthday;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

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

}
