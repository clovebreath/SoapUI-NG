package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.Api;
import io.mojohao.soapui_ng.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    List<Api> getAllApis(){
        return null;
    }
    Api queryApiById(int id){
        return null;
    }
    List<Api> queryApiByCondition(Api api){
        return null;
    }
    int deleteApiById(int id){
        return id;
    }
    int updateApi(Api api){
        return 0;
    }
    int insertApi(Api api){
        return 0;
    }

}
