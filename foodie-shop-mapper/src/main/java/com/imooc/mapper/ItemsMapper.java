package com.imooc.mapper;

import com.imooc.entitys.Items;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表
 *
 * @author TryAgain404
 * @email TryAgain500@163.com
 * @date 2020-10-21 11:33:24
 */
@Mapper
public interface ItemsMapper extends BaseMapper<Items> {

}
