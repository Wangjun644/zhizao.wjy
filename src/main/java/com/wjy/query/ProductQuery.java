package com.wjy.query;

import lombok.Data;

@Data
public class ProductQuery {
    private String productName;
    private String productNum;
    private Integer pageSize;
    private Integer currentPage;

}
