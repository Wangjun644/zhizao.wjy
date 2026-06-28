package com.wjy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;



@Data
@TableName("t_product")
public class Product {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer flag;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("create_userid")
    private Integer createUserid;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("update_userid")
    private Integer updateUserid;

    @TableField("product_num")
    private String productNum;

    @TableField("product_name")
    private String productName;

    @TableField("product_img_url")
    private String productImgUrl;

    @TableField("factory_id")
    private Integer factoryId;
}
