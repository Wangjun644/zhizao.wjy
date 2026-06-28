package com.wjy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_product_schedule")
public class ProductSchedule {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer flag;
    @TableField("creat_time")
    private LocalDateTime creatTime;
    @TableField("creat_userid")
    private Integer creatUserid;
    @TableField("update_time")
    private LocalDateTime updateTime;
    @TableField("update_userid")
    private Integer updateUserid;
    @TableField("schedule_seq")
    private String scheduleSeq;
    @TableField("schedule_count")
    private Integer scheduleCount;
    @TableField("schedule_status")
    private Integer scheduleStatus;
    @TableField("plan_id")
    private Integer planId;
    @TableField("product_id")
    private Integer productId;
    @TableField("equipment_id")
    private Integer equipmentId;
    @TableField("start_date")
    private LocalDateTime startDate;
    @TableField("end_date")
    private LocalDateTime endDate;
    @TableField("factory_id")
    private Integer factoryId;
}
