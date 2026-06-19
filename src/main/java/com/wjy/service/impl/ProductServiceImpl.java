package com.wjy.service.impl;

import com.wjy.domain.Product;
import com.wjy.mapper.ProductMapper;
import com.wjy.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product getOne(Integer id){
        Product product;
        product = productMapper.selectById(id);
        return product;
    }
}
