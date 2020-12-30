package com.imooc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.entitys.SysRole;

import java.util.Set;

/**
 * 角色业务层
 *
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2020-12-28 16:37
 */
public interface SysRoleService extends IService<SysRole> {

    Set<String> selectRolePermissionByUserId(String userId);
}
