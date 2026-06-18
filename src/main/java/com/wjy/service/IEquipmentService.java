package com.wjy.service;

import com.wjy.domain.Equipment;

import java.util.List;

public interface IEquipmentService {

    Equipment getOne(Integer id);

    List<Equipment> getList(Equipment equipment);
}
