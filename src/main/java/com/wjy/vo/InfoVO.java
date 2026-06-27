package com.wjy.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //饼状图 数据
    private List<Object> pie = null;
    //柱状图 数据
    private List<Long> bar = null;

}
