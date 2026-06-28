package com.wjy.service;

public interface IProductScheduleService {

    Long getCountByStatusAndFactoryId(Integer status, Integer factoryId);
}
