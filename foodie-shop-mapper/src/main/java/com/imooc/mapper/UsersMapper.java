package com.imooc.mapper;

import com.imooc.entitys.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户表 
 *
 * @author TryAgain404
 * @email TryAgain500@163.com
 * @date 2020-10-21 11:33:24
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

    Users getUsernameIsExit(@Param("username") String username);
}
