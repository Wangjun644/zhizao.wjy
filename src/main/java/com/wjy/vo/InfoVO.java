package com.wjy.vo;

import lombok.Data;

@Data
public class InfoVO {
    //开机率
    private Integer open;
    //故障率
    private Integer fault;
    //运行率
    private Integer run;
    //综合率
    private Integer general;
}
