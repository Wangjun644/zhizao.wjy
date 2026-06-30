package com.wjy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjy.domain.Product;
import com.wjy.domain.User;
import com.wjy.query.ProductQuery;
import com.wjy.service.IProductService;
import com.wjy.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

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



    @PostMapping("/getList")
    public R getList(@RequestBody Product product){
        List<Product> list = productService.getList(product);
        return R.success("查询成功",list);
//        return R.success("查询成功",productService.getList(product));
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id){
        //
        try{
            productService.delete(id);
            return R.success("删除成功");
        }catch (Exception e){
            String message = e.getMessage();
            e.printStackTrace();
            return R.fail(message);
        }
    }

//    @PostMapping("/add")
//    public R add(@RequestBody Product product){
//        productService.add(product);
//        return R.success("新增成功");
//    }

    @PostMapping("/add")
    public R add(Product product, @RequestParam("file") MultipartFile file, HttpSession session){
        //
        User user = (User)session.getAttribute("user");
        productService.add(product, file, user);
//        System.out.println(product);
//        System.out.println(file);
//        System.out.println(file.getOriginalFilename());
        return R.success("新增成功");
    }

    @PutMapping("/update")
    public R update(@RequestBody Product product, HttpSession session){
        User user = (User)session.getAttribute("user");
        //
        productService.update(product, user);
        return R.success("修改成功");
    }

    @PostMapping("/getPage")
    public R getPage(@RequestBody ProductQuery query) {
        //
        IPage<Product> page = productService.getPage(query);
        return R.success("查询成功", page);
    }
}
