package com.wjy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wjy.domain.Equipment;
import com.wjy.mapper.EquipmentMapper;
import com.wjy.service.IEquipmentService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EquipmentServiceImpl implements IEquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;


    @Override
    public Equipment getOne(Integer id){
        return equipmentMapper.selectById(id);
    }

    @Override
    public List<Equipment> getList(Equipment equipment){

        LambdaQueryWrapper<Equipment> wrapper = new LambdaQueryWrapper<>();
        String equipmentSeq = equipment.getEquipmentSeq();
        String equipmentName = equipment.getEquipmentName();

        wrapper.eq(!StringUtils.isBlank(equipmentSeq),
                        Equipment::getEquipmentSeq,equipmentSeq)
                .like(!StringUtils.isBlank(equipmentName),
                        Equipment::getEquipmentName,equipmentName);
        return equipmentMapper.selectList(wrapper);
    }

    @Override
    public void deleteById(Integer id) {
        equipmentMapper.deleteById(id);
    }

    @Override
    public void add(Equipment equipment) {
        equipment.setCreateTime(new Date());
        equipment.setCreateUserid(1);
        equipmentMapper.insert(equipment);
    }

    @Override
    public void update(Equipment equipment){
        Integer id = equipment.getId();
        if(id == null){
            System.out.println("id为空");
        }
        equipment.setUpdateTime(new Date());
        equipment.setUpdateUserid(1);
        equipmentMapper.updateById(equipment);
    }
}
