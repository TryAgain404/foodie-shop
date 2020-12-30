package com.imooc.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.entitys.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色信息表
 *
 * @author TryAgain404
 * @email TryAgain500@163.com
 * @date 2020-12-28 16:45:08
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> selectRolePermissionByUserId(String userId);

}
