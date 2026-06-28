package com.wjy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wjy.domain.ProductOrder;
import com.wjy.mapper.ProductOrderMapper;
import com.wjy.service.IProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOrderServiceImpl implements IProductOrderService {

    @Autowired
    private ProductOrderMapper productOrderMapper;
    @Override
    public Long getCountByStatusAndFactoryId(int status, Integer factoryId) {
        LambdaQueryWrapper<ProductOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProductOrder::getOrderStatus, status);
        wrapper.eq(ProductOrder::getFactoryId, factoryId);
        return productOrderMapper.selectCount(wrapper);
    }

    @Override
    public Long getCountByYearAndMonth(Integer year, Integer month) {
        return productOrderMapper.getCountByYearAndMonth(year, month);
    }

    @Override
    public List<ProductOrder> getByProductIdAndStatus(Integer productId, Integer status) {
        LambdaQueryWrapper<ProductOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProductOrder::getOrderStatus, status);
        wrapper.eq(ProductOrder::getProductId, productId);
        productOrderMapper.selectList(wrapper);
        return productOrderMapper.selectList(wrapper);

    }


//    public List<Integer> getMonthData(Integer year, Integer month) {
//        LambdaQueryWrapper<ProductOrder> wrapper = new LambdaQueryWrapper<>();
//
//
//    }
}
