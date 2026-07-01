package com.wjy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjy.domain.Equipment;
import com.wjy.domain.Product;
import com.wjy.query.EquQuery;

import java.util.List;

public interface IEquipmentService {

    Equipment getOne(Integer id);

    List<Equipment> getList(Equipment equipment);

    void deleteById(Integer id);

    void add(Equipment equipment);

    void update(Equipment equipment);

    Long getCountByFactoryId(Integer factoryId);

    Long getCountByStatusAndFactoryId(Integer i, Integer factoryId);

    IPage<Equipment> getPage(EquQuery query);
}
