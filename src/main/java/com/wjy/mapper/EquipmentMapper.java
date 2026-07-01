package com.wjy.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjy.domain.Equipment;

import java.util.List;

public interface EquipmentMapper extends BaseMapper<Equipment> {
    List<Equipment> getPage(String productName, String equName, Integer currentPage, Integer pageSize);
}