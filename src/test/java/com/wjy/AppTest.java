package com.wjy;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wjy.domain.Equipment;
import com.wjy.mapper.EquipmentMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


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

    @Test
    public void query() {
//        List<Equipment> equipment = equipmentMapper.selectBatchIds(Arrays.asList(1, 2));
//        equipment.forEach(System.out::println);

//          List<Equipment> equipment = equipmentMapper.selectList(null);
//          equipment.forEach(System.out::println);

//        Wrapper<Equipment> queryWrapper;
//        Long count = equipmentMapper.selectCount( queryWrapper:null);
//        System.out.println(count);


    }

    @Test
    public void query2() {
//        QueryWrapper<Equipment> wrapper = new QueryWrapper<>();
//        wrapper.eq( "equipment_name","设备一");
//        wrapper.eq( "equipment_seq","RQQ001");
//        List<Equipment> equipment = equipmentMapper.selectList(wrapper);
//        equipment.forEach(System.out::println);


//        LambdaQueryWrapper<Equipment> wrapper =new LambdaQueryWrapper<>();
//        wrapper.eq(Equipment::getEquipmentName,"设备二");
//        wrapper.eq(Equipment::getEquipmentSeq,"RQQ002");
//        List<Equipment> equipment=equipmentMapper.selectList((wrapper));
//        equipment.forEach((System.out::println));


//        LambdaQueryWrapper<Equipment> wrapper =new LambdaQueryWrapper<>();
//        wrapper.like(Equipment::getEquipmentName,"备");
//
//        List<Equipment> equipment = equipmentMapper.selectList((wrapper));
//        equipment.forEach(System.out::println);



//        LambdaQueryWrapper<Equipment> wrapper =new LambdaQueryWrapper<>();
//        wrapper.eq(Equipment::getEquipmentName,"设备一").or().eq(Equipment::getEquipmentName,"设备七");
//        List<Equipment> equipment = equipmentMapper.selectList((wrapper);
//        equipment.forEach(System.out::println);
    }





}

