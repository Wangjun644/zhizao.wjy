package com.wjy.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
@Data
@TableName("t_user")
public class User {
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
    @TableField("user_status")
    private Integer userStatus;
    @TableField("user_name")
    private String userName;
    @TableField("user_real_name")
    private String userRealName;
    @TableField("user_passwd")
    private String userPasswd;
    @TableField("user_job_num")
    private String userJobNum;
    @TableField("user_phone_num")
    private String userPhoneNum;
    @TableField("user_email")
    private String userEmail;
    @TableField("role_id")
    private Integer roleId;
    @TableField("factory_id")
    private Integer factoryId;
}
