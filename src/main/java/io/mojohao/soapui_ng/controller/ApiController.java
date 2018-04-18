package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.Api;
import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.service.ApiService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @ResponseBody
    @RequestMapping(value = "/all")
    public List<Api> getAllApis(){
        return apiService.getAllApis();
    }

    @ResponseBody
    @RequestMapping(value = "/query")
    public List<Api> queryApiByCondition(String apiId,String apiName,String apiType){
        Api api=new Api();
        if(StringUtils.isNotBlank(apiId)&&StringUtils.isNumeric(apiId)){
            api.setApiId(Integer.parseInt(apiId));
        }
        if(StringUtils.isNotBlank(apiName)){
            api.setApiName(apiName);
        }
        if(StringUtils.isNotBlank(apiType)){
            api.setApiType(apiType);
        }
        return apiService.queryApiByCondition(api);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    int deleteApiById(int id){
        return apiService.deleteApiById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    int updateApi(Api api){
        return apiService.updateApi(api);
    }

    @ResponseBody
    @RequestMapping(value = "/insert")
    int insertApi(Api api){
        return apiService.insertApi(api);
    }

    @ResponseBody
    @RequestMapping(value = "/categoryByType")
    public List<ChartTypeDto> categoryByType() {
        return apiService.categoryByType();
    }

    @ResponseBody
    @RequestMapping(value = "/categoryByAccessMode")
    public List<ChartTypeDto> categoryByAccessMode() {
        return apiService.categoryByAccessMode();
    }


}
