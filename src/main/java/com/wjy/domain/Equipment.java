package com.wjy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("t_equipment")
public class Equipment {
    //ORM 对象关系映射： 把数据库中的字段映射（赋值）到java对象字段

    //主键 AUTO 自动增长
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer flag;
    //字段映射
    @TableField("create_time")
    private Date createTime;
    @TableField("create_userid")
    private Integer createUserid;
    @TableField("update_time")
    private Date updateTime;
    @TableField("update_userid")
    private Integer updateUserid;
    @TableField("equipment_seq")
    private String equipmentSeq;
    @TableField("equipment_name")
    private String equipmentName;
    @TableField("equipment_img_url")
    private String equipmentImgUrl;
    @TableField("equipment_status")
    private Integer equipmentStatus;
    @TableField("factory_id")
    private Integer factoryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(Integer createUserid) {
        this.createUserid = createUserid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(Integer updateUserid) {
        this.updateUserid = updateUserid;
    }

    public String getEquipmentSeq() {
        return equipmentSeq;
    }

    public void setEquipmentSeq(String equipmentSeq) {
        this.equipmentSeq = equipmentSeq;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentImgUrl() {
        return equipmentImgUrl;
    }

    public void setEquipmentImgUrl(String equipmentImgUrl) {
        this.equipmentImgUrl = equipmentImgUrl;
    }

    public Integer getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(Integer equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", flag=" + flag +
                ", createTime=" + createTime +
                ", createUserid=" + createUserid +
                ", updateTime=" + updateTime +
                ", updateUserid=" + updateUserid +
                ", equipmentSeq='" + equipmentSeq + '\'' +
                ", equipmentName='" + equipmentName + '\'' +
                ", equipmentImgUrl='" + equipmentImgUrl + '\'' +
                ", equipmentStatus=" + equipmentStatus +
                ", factoryId=" + factoryId +
                '}';
    }
}