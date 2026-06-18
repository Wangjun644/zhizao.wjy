package com.wjy.controller;

import com.wjy.domain.Equipment;
import com.wjy.service.IEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equ")
public class EquipmentController {


    @Autowired
    private IEquipmentService equipmentService;

    @GetMapping("/getOne/{id}")
    public Equipment getOne(@PathVariable Integer id){
        return equipmentService.getOne(id);
    }
}
