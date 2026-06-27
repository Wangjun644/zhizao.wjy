package com.wjy.service;

import com.wjy.domain.Factory;

public interface IProductOrderService {


    Long getCountByStatusAndFactoryId(int i, Integer factoryId);
}
