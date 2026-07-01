package com.wjy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjy.domain.Equipment;
import com.wjy.domain.Product;
import com.wjy.domain.User;
import com.wjy.query.EquQuery;
import com.wjy.query.ProductQuery;
import com.wjy.service.IEquipmentService;
import com.wjy.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {


    @Autowired
    private IEquipmentService equipmentService;

    @GetMapping("/getOne/{id}")
    public R getOne(@PathVariable Integer id){
        return R.success("查询成功",equipmentipmentService.getOne(id));
    }


    @PostMapping("/getList")
    public R getList(@RequestBody Equipment equipmentipment){

        System.out.println(equipmentipment);

        return R.success("查询成功",equipmentService.getList(equipmentipment));
    }

    @DeleteMapping("/{id}")
    public R deleteById(@PathVariable Integer id){
        equipmentService.deleteById(id);
        return R.success("删除成功");
    }

//    @PostMapping("/add")
//    public R add(@RequipmentestBody Equipment equipmentipment){
//        equipmentipmentService.add(equipmentipment);
//        return R.success("新增成功");
//    }

    @PostMapping("/add")
    public R add(Equipment equipment, @RequestParam("file") MultipartFile file, HttpSession session){
        //
        User user = (User)session.getAttribute("user");
        equipmentService.add(equipment, file, user);
//        System.out.println(equipment);
//        System.out.println(file);
//        System.out.println(file.getOriginalFilename());
        return R.success("新增成功");
    }

    @PostMapping
    public R update(@RequestBody Equipment equipmentipment){
        equipmentService.update(equipmentipment);
        return R.success("修改成功");
    }

    @PostMapping("/getPage")
    public R getPage(@RequestBody EquQuery query) {
        //
        IPage<Equipment> page = equipmentService.getPage(query);
        return R.success("查询成功", page);
    }

}
