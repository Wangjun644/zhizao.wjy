package com.wjy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@TableName("t_factory")
public class Factory {

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
    private String bak;

    @TableField("factory_name")
    private String factoryName;
    @TableField("factory_img_url")
    private String factoryImgUrl;
    @TableField("factory_addr")
    private String factoryAddr;
    @TableField("factory_url")
    private String factoryUrl;
    @TableField("factory_worker")
    private Integer factoryWorker;
    @TableField("factory_status")
    private Integer factoryStatus;

}
