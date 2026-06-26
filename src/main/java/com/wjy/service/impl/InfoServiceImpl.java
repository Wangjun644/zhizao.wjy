package com.wjy.service.impl;

import com.wjy.service.IEquipmentService;
import com.wjy.service.IInfoService;
import com.wjy.service.IProductScheduleService;
import com.wjy.service.IProductService;
import com.wjy.vo.InfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements IInfoService {

















    //
    @Autowired
    private IEquipmentService equipmentService;
    //
    @Autowired
    private IProductScheduleService productScheduleService;

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
        //2、
        InfoVO infoVO = new InfoVO();
        infoVO.setGeneral(general);
        infoVO.setOpen(open);
        infoVO.setFault(fault);
        infoVO.setRun(run);
        return infoVO;
    }
}
