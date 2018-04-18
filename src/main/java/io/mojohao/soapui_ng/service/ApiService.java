package io.mojohao.soapui_ng.service;

import io.mojohao.soapui_ng.entity.Api;
import io.mojohao.soapui_ng.entity.ChartTypeDto;

import java.util.List;

public interface ApiService {
    List<Api> getAllApis();
    Api queryApiById(int id);
    List<Api> queryApiByCondition(Api api);
    int deleteApiById(int id);
    int updateApi(Api api);
    int insertApi(Api api);
    List<ChartTypeDto> categoryByType();
    List<ChartTypeDto> categoryByAccessMode();
}
