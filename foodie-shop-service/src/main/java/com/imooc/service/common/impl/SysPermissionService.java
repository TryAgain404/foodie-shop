package com.imooc.service.common.impl;

import com.imooc.entitys.Users;
import com.imooc.service.SysMenuService;
import com.imooc.service.SysRoleService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户权限处理
 *
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2020-12-28 16:35
 */
@Component
public class SysPermissionService {

    private final String SUPER_ADMIN = "201125CAWB3N6T0H";

    private final SysRoleService roleService;

    private final SysMenuService menuService;

    public SysPermissionService(SysRoleService roleService, SysMenuService menuService) {
        this.roleService = roleService;
        this.menuService = menuService;
    }

    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(Users user) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (SUPER_ADMIN.equals(user.getId())) {
            roles.add("admin");
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getId()));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(Users user) {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (SUPER_ADMIN.equals(user.getId())) {
            perms.add("*:*:*");
        } else {
            perms.addAll(menuService.selectMenuPermsByUserId(user.getId()));
        }
        return perms;
    }
}
