package io.mojohao.soapui_ng.service.impl;

import io.mojohao.soapui_ng.dao.ApiDao;
import io.mojohao.soapui_ng.entity.Api;
import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiDao apiDao;

    @Override
    public List<Api> getAllApis() {
        return apiDao.getAllApis();
    }

    @Override
    public Api queryApiById(int id) {
        return apiDao.queryApiById(id);
    }

    @Override
    public List<Api> queryApiByCondition(Api api) {
        return apiDao.queryApiByCondition(api);
    }

    @Override
    public List<Api> queryApiByPage(Map map) {
        return apiDao.queryApiByPage(map);
    }

    @Override
    public int deleteApiById(int id) {
        return apiDao.deleteApiById(id);
    }

    @Override
    public int updateApi(Api api) {
        return apiDao.updateApi(api);
    }

    @Override
    public int insertApi(Api api) {
        return apiDao.insertApi(api);
    }

    @Override
    public int queryAmount(Map param) {
        return apiDao.queryAmount(param);
    }

    @Override
    public List<ChartTypeDto> categoryByType() {
        return apiDao.categoryByType();
    }

    @Override
    public List<ChartTypeDto> categoryByAccessMode() {
        return apiDao.categoryByAccessMode();
    }


}
