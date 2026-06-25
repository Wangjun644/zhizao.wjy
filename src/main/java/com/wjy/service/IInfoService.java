package com.wjy.service;

import com.wjy.vo.InfoVO;

public interface IInfoService {
    @Override
    public InfoVO getInfo(Integer factoryId);
}
