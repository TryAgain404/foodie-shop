package com.imooc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.entitys.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单权限表
 *
 * @author TryAgain404
 * @email TryAgain500@163.com
 * @date 2020-12-28 16:45:08
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<String> selectMenuPermsByUserId(String userId);
}
