package com.imooc.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.entitys.ItemsComments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.entitys.vo.ItemCommentVO;
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
     * @param page
     * @param itemId
     * @param level
     * @return
     */
    IPage<ItemCommentVO> queryItemComments(IPage<ItemCommentVO> page, @Param("itemId") String itemId,
                                           @Param("level") Integer level);

    /**
     * 用于展示搜索商品
     * @param page 分页
     * @param keyword 关键词
     * @param sort 排序
     * @return
     */
    IPage<ItemCommentVO> searchItemsByThirdCat(IPage<ItemCommentVO> page, @Param("keyword") String keyword,
                                           @Param("sort") String sort);
}
