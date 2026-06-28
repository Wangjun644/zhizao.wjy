package com.wjy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wjy.domain.ProductSchedule;
import com.wjy.mapper.ProductScheduleMapper;
import com.wjy.service.IProductScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductScheduleServiceImpl implements IProductScheduleService {

    @Autowired
    private ProductScheduleMapper productScheduleMapper;

    @Override
    public Long getCountByStatusAndFactoryId(Integer status, Integer factoryId) {
        LambdaQueryWrapper<ProductSchedule> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProductSchedule::getScheduleStatus, status);
        wrapper.eq(ProductSchedule::getFactoryId, factoryId);
        return productScheduleMapper.selectCount(wrapper);

    }
}
