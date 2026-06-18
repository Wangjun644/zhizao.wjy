package com.wjy.service.impl;

import com.wjy.domain.Equipment;
import com.wjy.mapper.EquipmentMapper;
import com.wjy.service.IEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentServiceImpl implements IEquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;


    @Override
    public Equipment getOne(Integer id){
        return equipmentMapper.selectById(id);
    }
}
