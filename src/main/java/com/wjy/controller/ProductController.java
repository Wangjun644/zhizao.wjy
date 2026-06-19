package com.wjy.controller;

import com.wjy.domain.Product;
import com.wjy.service.IProductService;
import com.wjy.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private IProductService productService;

    @GetMapping("/getOne/{id}")
    public R getOne(@PathVariable Integer id){
        Product product = productService.getOne(id);
        return R.success("查询成功",product);
    }



}
