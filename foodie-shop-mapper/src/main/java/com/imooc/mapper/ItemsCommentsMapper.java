package com.imooc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.entitys.ItemsComments;
import com.imooc.entitys.vo.ItemCommentVO;
import com.imooc.entitys.vo.ShopCartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品评价表 
 *
 * @author TryAgain404
 * @email TryAgain500@163.com
 * @date 2020-10-21 11:33:24
 */
@Mapper
public interface ItemsCommentsMapper extends BaseMapper<ItemsComments> {

    /**
     * 获取好评
     * @param page 分页
     * @param itemId 商品ID
     * @param level 评论等级
     * @return  商品评价的VO
     */
    IPage<ItemCommentVO> queryItemComments(IPage<ItemCommentVO> page, @Param("itemId") String itemId,
                                           @Param("level") Integer level);

    /**
     * 用于展示关键词搜索商品
     * @param page 分页
     * @param keyword 关键词
     * @param sort 排序
     * @return 商品评价的VO
     */
    IPage<ItemCommentVO> searchItems(IPage<ItemCommentVO> page, @Param("keyword") String keyword,
                                           @Param("sort") String sort);

    /**
     * 根据商品的id搜索商品
     * @param page 分页
     * @param catId 关键词
     * @param sort 排序
     * @return 商品评价的VO
     */
    IPage<ItemCommentVO> searchItemsByThirdCat(IPage<ItemCommentVO> page, @Param("catId") String catId,
                                               @Param("sort") String sort);

    List<ShopCartVO> queryItemsBySpecIds(@Param("paramsList") List<String> specIdsList);
}
