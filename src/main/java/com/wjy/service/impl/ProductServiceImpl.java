package com.wjy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjy.domain.Product;
import com.wjy.domain.ProductOrder;
import com.wjy.domain.User;
import com.wjy.mapper.ProductMapper;
import com.wjy.query.ProductQuery;
import com.wjy.service.IProductOrderService;
import com.wjy.service.IProductService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;  // ← Spring 自带的
import org.junit.platform.commons.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private IProductOrderService productOrderService;

    @Override
    public Product getOne(Integer id){
        Product product= productMapper.selectById(id);
        return product;
    }

    @Override
    public List<Product> getList(Product product) {

        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();

        wrapper.like(StringUtils.isNotBlank(product.getProductName()),
                Product::getProductName,product.getProductName());
        //
        wrapper.like(StringUtils.isNotBlank(product.getProductNum()),
                Product::getProductNum,product.getProductNum());
        //
        return productMapper.selectList(wrapper);
    }

    @Override
    public void delete(Integer id) {
        //
        List <ProductOrder> list = productOrderService.getByProductIdAndStatus(id, 20);
        if(list.size() > 0){
            throw new RuntimeException("产品关联订单，无法删除！");
        }
        //
        productMapper.deleteById(id);
    }

    @Override
    public void add(Product product, MultipartFile file, User user) {
        //
        //
        try {
            InputStream in = file.getInputStream();
            String name = file.getOriginalFilename();
            //
            String ASSETS_PATH = System.getProperty("user.dir");
            String filePath = ASSETS_PATH + "/upload/" + name;
            FileOutputStream out = new FileOutputStream(filePath);
            IOUtils.copy(in, out);
            //
            product.setCreateTime(LocalDateTime.now());
            product.setFactoryId(user.getFactoryId());
            product.setCreateUserid(user.getId());
            product.setProductImgUrl("/upload/" + name);
            productMapper.insert(product);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Product product, User user) {
        product.setUpdateTime(LocalDateTime.now());
        product.setUpdateUserid(user.getId());
        productMapper.updateById(product);
    }

    @Override
    public IPage<Product> getPage(ProductQuery query) {

        String productName = query.getProductName();
        String productNum = query.getProductNum();

        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();

        wrapper.like(StringUtils.isNotBlank(productName),
                Product::getProductName,productName);

        wrapper.like(StringUtils.isNotBlank(productNum),
                Product::getProductNum,productNum);

        IPage<Product> page = new Page<>();
        page.setCurrent(query.getCurrentPage());
        page.setSize(query.getPageSize());

        IPage<Product> productIPage = productMapper.selectPage(page, wrapper);
        return productIPage;
    }

}