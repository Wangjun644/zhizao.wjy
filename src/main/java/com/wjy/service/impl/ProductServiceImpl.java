package com.wjy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wjy.domain.Product;
import com.wjy.mapper.ProductMapper;
import com.wjy.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;  // ← Spring 自带的

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product getOne(Integer id){
        Product product= productMapper.selectById(id);
        return product;
    }

    @Override
    public List<Product> getList(Product product) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();

        // 产品编号 - 精确匹配（非空且不为空字符串时才加条件）
        wrapper.eq(StringUtils.hasText(product.getProductNum()),
                Product::getProductNum, product.getProductNum());

        // 产品名称 - 模糊匹配（非空且不为空字符串时才加条件）
        wrapper.like(StringUtils.hasText(product.getProductName()),
                Product::getProductName, product.getProductName());

        // 工厂ID - 精确匹配（不为null时才加条件）
        wrapper.eq(product.getFactoryId() != null,
                Product::getFactoryId, product.getFactoryId());

        return productMapper.selectList(wrapper);
    }

    @Override
    public void add(Product product) {
        product.setCreateTime(LocalDateTime.now());
        product.setCreateUserid(1);
        productMapper.insert(product);
    }

    @Override
    public void update(Product product) {
        product.setUpdateTime(LocalDateTime.now());
        product.setUpdateUserid(1);
        productMapper.updateById(product);
    }

    @Override
    public void delete(Integer id) {
        productMapper.deleteById(id);
    }
}