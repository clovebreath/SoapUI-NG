package io.mojohao.soapui_ng.service.impl;

import io.mojohao.soapui_ng.dao.ApiDao;
import io.mojohao.soapui_ng.entity.Api;
import io.mojohao.soapui_ng.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiDao apiDao;

    public List<Api> getAllApis() {
        return apiDao.getAllApis();
    }

    public Api queryApiById(int id) {
        return apiDao.queryApiById(id);
    }

    public List<Api> queryApiByCondition(Api api) {
        return apiDao.queryApiByCondition(api);
    }

    public int deleteApiById(int id) {
        return apiDao.deleteApiById(id);
    }

    public int updateApi(Api api) {
        return apiDao.updateApi(api);
    }

    public int insertApi(Api api) {
        return apiDao.insertApi(api);
    }
}
