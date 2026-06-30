package com.wjy.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjy.domain.Product;
import com.wjy.domain.User;
import com.wjy.query.ProductQuery;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IProductService {


    Product getOne(Integer id);

    List<Product> getList(Product product);

    void delete(Integer id);

    void add(Product product, MultipartFile file, User user);

    void update(Product product, User user);

    IPage<Product> getPage(ProductQuery query);
}
