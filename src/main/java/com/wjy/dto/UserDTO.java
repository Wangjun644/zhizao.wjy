package com.wjy.dto;

import lombok.Data;

@Data
//DTO 数据传输对象 VO 展示数据对象
public class UserDTO {
    private String userName;
    private String userPasswd;
    private String userRealName;
    private String factoryName;
}
