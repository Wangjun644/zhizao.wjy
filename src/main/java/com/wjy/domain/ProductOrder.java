package com.wjy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.util.Date;

@Data
@TableName("t_product_order")
public class ProductOrder {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer flag;
    @TableField("create_time")
    private Date createTime;
    @TableField("create_userid")
    private Integer createUserid;
    @TableField("update_time")
    private Date updateTime;
    @TableField("update_userid")
    private Integer updateUserid;
    @TableField("order_seq")
    private String orderSeq;
    @TableField("order_source")
    private Integer orderSource;
    @TableField("product_id")
    private Integer productId;
    @TableField("product_count")
    private Integer productCount;
    @TableField("end_date")
    private Date endDate;
    @TableField("order_status")
    private Integer orderStatus;
    @TableField("factory_id")
    private Integer factoryId;
    @TableField("factory_yield")
    private Integer factoryYield;

}
