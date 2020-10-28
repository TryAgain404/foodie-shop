package com.imooc.service.impl;

import com.imooc.entitys.ItemsSpec;
import com.imooc.mapper.ItemsSpecMapper;
import com.imooc.service.ItemsSpecService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service("itemsSpecService")
public class ItemsSpecServiceImpl extends ServiceImpl<ItemsSpecMapper, ItemsSpec> implements ItemsSpecService {


}
