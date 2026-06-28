package com.wjy.service;

import com.wjy.domain.Factory;
import com.wjy.domain.ProductOrder;

import java.util.List;

public interface IProductOrderService {


    Long getCountByStatusAndFactoryId(int i, Integer factoryId);

    Long getCountByYearAndMonth(Integer year, Integer month);

    List<ProductOrder> getByProductIdAndStatus(Integer productId, Integer status);
}
