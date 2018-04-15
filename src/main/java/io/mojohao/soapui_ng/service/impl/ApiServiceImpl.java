package io.mojohao.soapui_ng.service.impl;

import io.mojohao.soapui_ng.entity.Api;
import io.mojohao.soapui_ng.service.ApiService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {
    public List<Api> getAllApis() {
        return null;
    }

    public Api queryApiById(int id) {
        return null;
    }

    public List<Api> queryApiByCondition(Api api) {
        return null;
    }

    public int deleteApiById(int id) {
        return 0;
    }

    public int updateApi(Api api) {
        return 0;
    }

    public int insertApi(Api api) {
        return 0;
    }
}
