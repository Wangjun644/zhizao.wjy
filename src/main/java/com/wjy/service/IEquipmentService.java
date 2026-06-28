package com.wjy.service;

import com.wjy.domain.Equipment;

import java.util.List;

public interface IEquipmentService {

    Equipment getOne(Integer id);

    List<Equipment> getList(Equipment equipment);

    void deleteById(Integer id);

    void add(Equipment equipment);

    void update(Equipment equipment);

    Long getCountByFactoryId(Integer factoryId);

    Long getCountByStatusAndFactoryId(Integer i, Integer factoryId);
}
