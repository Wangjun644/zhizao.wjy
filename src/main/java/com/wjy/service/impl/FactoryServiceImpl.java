package com.wjy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wjy.domain.Factory;
import com.wjy.mapper.FactoryMapper;
import com.wjy.service.IFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;






@Service
public class FactoryServiceImpl implements IFactoryService {

    //
    @Autowired
    private FactoryMapper factoryMapper;


    @Override
    public Factory getOneByName(String factoryName){
        LambdaQueryWrapper<Factory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Factory::getFactoryName,factoryName);
        return factoryMapper.selectOne(wrapper);
    }

    @Override
    public Integer save(Factory factory) {
        factoryMapper.insert(factory);
        return factory.getId();
    }
}
