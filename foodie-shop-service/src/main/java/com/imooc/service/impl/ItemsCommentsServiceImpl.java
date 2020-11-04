package com.imooc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.entitys.ItemsComments;
import com.imooc.entitys.vo.CommentLevelCountsVO;
import com.imooc.entitys.vo.ItemCommentVO;
import com.imooc.mapper.ItemsCommentsMapper;
import com.imooc.service.ItemsCommentsService;
import com.imooc.utils.PageUtils;
import com.imooc.utils.enums.Level;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * @author TryAgain404
 */
@Service("itemsCommentsService")
public class ItemsCommentsServiceImpl extends ServiceImpl<ItemsCommentsMapper, ItemsComments> implements ItemsCommentsService {


    @Override
    public PageUtils queryPage(String itemId, Integer level,
                               Integer pages, Integer pageSize) {

        IPage<ItemCommentVO> page = baseMapper.queryItemComments((
                new Page<>(pages,
                        pageSize)), itemId, level);

        return new PageUtils(page);
    }

    @Override
    public CommentLevelCountsVO getCommentLevel(String itemId) {
        int goodCounts = getLevelCount(itemId, Level.GOOD.type);
        int normalCounts = getLevelCount(itemId, Level.NORMA.type);
        int badCounts = getLevelCount(itemId, Level.BAD.type);
        int totalCounts = goodCounts + normalCounts + badCounts;
        CommentLevelCountsVO commentLevelCountsVO = new CommentLevelCountsVO();

        commentLevelCountsVO.setBadCounts(badCounts);
        commentLevelCountsVO.setGoodCounts(goodCounts);
        commentLevelCountsVO.setNormalCounts(normalCounts);
        commentLevelCountsVO.setTotalCounts(totalCounts);

        return commentLevelCountsVO;
    }

    private int getLevelCount(String itemId, Integer level) {
        return count(new QueryWrapper<ItemsComments>().lambda().eq(ItemsComments::getItemId, itemId)
                .eq(ItemsComments::getCommentLevel, level));
    }
}
