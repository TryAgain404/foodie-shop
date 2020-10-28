package com.imooc.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.entitys.PdmanDbVersion;
import com.imooc.mapper.PdmanDbVersionMapper;
import com.imooc.service.PdmanDbVersionService;
import org.springframework.stereotype.Service;

@Service("pdmanDbVersionService")
public class PdmanDbVersionServiceImpl extends ServiceImpl<PdmanDbVersionMapper, PdmanDbVersion> implements PdmanDbVersionService {


}
