package com.wjy;
import com.wjy.domain.Equipment;
import com.wjy.mapper.EquipmentMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest(classes = App.class)
public class AppTest {

    //DI
    @Autowired
    private EquipmentMapper equipmentMapper;

    @Test
    public void test() {
        Equipment equipment = equipmentMapper.selectById(1);
        System.out.println(equipment);
    }



    @Test
    public void insert(){
        Equipment equipment = new Equipment();
        equipment.setFlag(0);
        equipment.setCreateTime(new Date());
        equipment.setCreateUserid(1);
        equipment.setEquipmentSeq("RQQ005");
        equipment.setEquipmentName("设备五");
        equipment.setFactoryId(1);
        equipmentMapper.insert(equipment);
    }

    @Test
    public void delete(){
        equipmentMapper.deleteById(6);
    }

    @Test
    public void update(){
        Equipment equipment = new Equipment();
        equipment.setId(7);
        equipment.setEquipmentSeq("RQQ007");
        equipment.setEquipmentName("设备七");
        equipmentMapper.updateById((equipment));
    }
}

