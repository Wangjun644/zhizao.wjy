package com.wjy.query;

import lombok.Data;

@Data
public class EquQuery {
    private String equName;
    private Integer pageSize;
    private Integer currentPage;
}
