package com.wjy.service;


import com.wjy.domain.Product;

import java.util.List;

public interface IProductService {


    Product getOne(Integer id);

    List<Product> getList(Product product);

    void delete(Integer id);

    void add(Product product);

    void update(Product product);
}
