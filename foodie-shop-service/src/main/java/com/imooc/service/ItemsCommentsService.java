package com.imooc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.entitys.ItemsComments;
import com.imooc.entitys.vo.CommentLevelCountsVO;
import com.imooc.utils.PageUtils;

import java.util.Map;

/**
 * @author TryAgain404
 * @date 2020-11-4 14:23
 */
public interface ItemsCommentsService extends IService<ItemsComments> {

    PageUtils queryPage(String itemId, Integer level,
                        Integer page, Integer pageSize);

    CommentLevelCountsVO getCommentLevel(String itemId);

    PageUtils searchPage(String keyword, String sort,
                        Integer page, Integer pageSize);
}
