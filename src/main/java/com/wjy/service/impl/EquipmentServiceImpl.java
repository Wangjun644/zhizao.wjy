package com.wjy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjy.domain.Equipment;
import com.wjy.domain.Product;
import com.wjy.mapper.EquipmentMapper;
import com.wjy.query.EquQuery;
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

    @Override
    public Long getCountByFactoryId(Integer factoryId) {
        LambdaQueryWrapper<Equipment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Equipment::getFactoryId, factoryId);
        return equipmentMapper.selectCount(wrapper);
    }


    @Override
    public Long getCountByStatusAndFactoryId(Integer status, Integer factoryId) {
        LambdaQueryWrapper<Equipment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Equipment::getEquipmentStatus, status);
        wrapper.eq(Equipment::getFactoryId, factoryId);
        return equipmentMapper.selectCount(wrapper);
    }

    @Override
    public IPage<Equipment> getPage(EquQuery query) {
//        String productName = query.getProductName();
        String equName = query.getEquName();

        //
        LambdaQueryWrapper<Equipment> wrapper = new LambdaQueryWrapper<>();
        //
        wrapper.like(StringUtils.isNotBlank(equName),
                Equipment::getEquipmentName,equName);
        //
        //
        IPage<Equipment> page = new Page<>();
        page.setCurrent(query.getCurrentPage());
        page.setSize(query.getPageSize());

        IPage<Equipment> productIPage = equipmentMapper.selectPage(page, wrapper);
        return productIPage;
    }
}

