package com.wjy.service.impl;

import com.wjy.service.*;
import com.wjy.vo.InfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InfoServiceImpl implements IInfoService {





















    //
    @Autowired
    private IEquipmentService equipmentService;
    //
    @Autowired
    private IProductScheduleService productScheduleService;
    //
    @Autowired
    private IProductOrderService productOrderService;

    @Override
    public InfoVO getInfo(Integer factoryId) {
        //
        Integer open = 0;
        //
        Integer fault = 0;
        //
        Integer run = 0;
        //
        Integer general = 0;
        //1、
        //1.1 查询总设备数
        Long equipmentCount = equipmentService.getCountByFactoryId(factoryId);
        System.out.println("总设备数：" + equipmentCount);
        //1.2 查询 加工中 总数  t_product_achedule schedule_status = 20
        Long jgCount = productScheduleService.getCountByStatusAndFactoryId(20, factoryId);
        //1.2 查询 待机 总数  t_product_achedule schedule_status = 10
        Long djCount = productScheduleService.getCountByStatusAndFactoryId(10, factoryId);
        //1.3 开机率
        if(equipmentCount != 0) {
            open = (int) ((jgCount + djCount) * 100 / equipmentCount);
            //1.4 故障率  equipment_status = 30
            Long equipmentFaultCount = equipmentService.getCountByStatusAndFactoryId(30, factoryId);
            fault = (int) (equipmentFaultCount * 100 / equipmentCount);
            //1.5 运行率
            run = (int) (jgCount * 100 / equipmentCount);
        }
        //1.6 综合率  equipment_status = 10
        Long equipmentStartCount = equipmentService.getCountByStatusAndFactoryId(10, factoryId);
        if(equipmentStartCount != 0) {
            general = (int) (jgCount * 100 / equipmentStartCount);
        }


        //1.7
        //未接单
        Long wjCount = productOrderService.getCountByStatusAndFactoryId(10, factoryId);
        //已接单
        Long jjCount = productOrderService.getCountByStatusAndFactoryId(20, factoryId);
        //已拒绝
        Long yjCount = productOrderService.getCountByStatusAndFactoryId(30, factoryId);
        //生产中
        Long scCount = productOrderService.getCountByStatusAndFactoryId(40, factoryId);
        //订单完成
        Long wcCount = productOrderService.getCountByStatusAndFactoryId(50, factoryId);
        List<Object> pie = new ArrayList<>();
        Map<String,Object> wj = new HashMap<>();
        //
        wj.put("value",wjCount);
        wj.put("name","待接单");
        //
        Map<String,Object> jj = new HashMap<>();
        jj.put("value",jjCount);
        jj.put("name","已接单");
        //
        Map<String,Object> yj = new HashMap<>();
        yj.put("value",yjCount);
        yj.put("name","已拒绝");
        //
        Map<String,Object> sc = new HashMap<>();
        sc.put("value",scCount);
        sc.put("name","生产中");
        //
        Map<String,Object> wc = new HashMap<>();
        wc.put("value",wcCount);
        wc.put("name","订单完成");
        pie.add(wj);
        pie.add(jj);
        pie.add(yj);
        pie.add(sc);
        pie.add(wc);

        //2、
        InfoVO infoVO = new InfoVO();
        infoVO.setGeneral(general);
        infoVO.setOpen(open);
        infoVO.setFault(fault);
        infoVO.setRun(run);
        infoVO.setPie(pie);
        return infoVO;
    }
}
