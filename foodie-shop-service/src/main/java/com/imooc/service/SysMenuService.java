package com.imooc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.entitys.SysMenu;

import java.util.Set;

/**
 * @author TryAgain404
 * @date 2020-12-28 16:50
 */
public interface SysMenuService extends IService<SysMenu> {

    Set<String> selectMenuPermsByUserId(String userId);

}
