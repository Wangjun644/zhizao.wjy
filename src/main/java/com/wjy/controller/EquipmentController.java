package com.wjy.controller;

import com.wjy.domain.Equipment;
import com.wjy.service.IEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equ")
public class EquipmentController {


    @Autowired
    private IEquipmentService equipmentService;

    @GetMapping("/getOne/{id}")
    public Equipment getOne(@PathVariable Integer id){
        return equipmentService.getOne(id);
    }


    @PostMapping("/getList")
    public List<Equipment> getList(@RequestBody Equipment equipment){

        System.out.println(equipment);

        return equipmentService.getList(equipment);
    }
}
