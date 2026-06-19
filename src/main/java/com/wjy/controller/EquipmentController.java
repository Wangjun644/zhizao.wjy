package com.wjy.controller;

import com.wjy.domain.Equipment;
import com.wjy.service.IEquipmentService;
import com.wjy.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equ")
public class EquipmentController {


    @Autowired
    private IEquipmentService equipmentService;

    @GetMapping("/getOne/{id}")
    public R getOne(@PathVariable Integer id){
        return R.success("查询成功",equipmentService.getOne(id));
    }


    @PostMapping("/getList")
    public R getList(@RequestBody Equipment equipment){

        System.out.println(equipment);

        return R.success("查询成功",equipmentService.getList(equipment));
    }

    @DeleteMapping("/{id}")
    public R deleteById(@PathVariable Integer id){
        equipmentService.deleteById(id);
        return R.success("删除成功");
    }

    @PostMapping("/add")
    public R add(@RequestBody Equipment equipment){
        equipmentService.add(equipment);
        return R.success("新增成功");
    }

    @PostMapping
    public R update(@RequestBody Equipment equipment){
        equipmentService.update(equipment);
        return R.success("修改成功");
    }

}
