package com.wjy.service;

import com.wjy.domain.Factory;



public interface IFactoryService {

    Factory getOneByName(String factoryName);

    Integer save(Factory factory);
}
