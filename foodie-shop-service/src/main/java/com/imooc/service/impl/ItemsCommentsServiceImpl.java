package com.imooc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.imooc.entitys.ItemsComments;
import com.imooc.mapper.ItemsCommentsMapper;
import com.imooc.service.ItemsCommentsService;
import com.imooc.utils.PageUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("itemsCommentsService")
public class ItemsCommentsServiceImpl extends ServiceImpl<ItemsCommentsMapper, ItemsComments> implements ItemsCommentsService {



}
