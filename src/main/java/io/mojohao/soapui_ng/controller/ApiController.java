package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.Api;
import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.service.ApiService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    /**
     * 查询全部
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/all")
    public List<Api> getAllApis(){
        return apiService.getAllApis();
    }

    /**
     * 根据条件查询
     * @param apiId
     * @param apiName
     * @param apiType
     * @param currPage
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query")
    public HashMap queryApiByPage(String apiId,String apiName,String apiType,int currPage ){
        Map param=new HashMap<String,Object>();
        HashMap<String,Object> retMap=new HashMap<>();
        if(StringUtils.isNotBlank(apiId)&&StringUtils.isNumeric(apiId)){
            param.put("apiId",Integer.parseInt(apiId));
        }
        if(StringUtils.isNotBlank(apiName)){
            param.put("apiName",apiName);
        }
        if(StringUtils.isNotBlank(apiType)){
            param.put("apiType",apiType);
        }
        if(currPage<=0) {
            currPage = 1;
        }
        param.put("currPage",currPage);
        retMap.put("list",apiService.queryApiByPage(param));
        retMap.put("total",apiService.queryAmount(param));
        return retMap;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    int deleteApiById(int id){
        int ret=0;
        try{
            ret=apiService.deleteApiById(id);
        }catch (Exception e){
            ret=-1;
        }
        return ret;
    }

    /**
     * 更新
     * @param api
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update")
    int updateApi(Api api){
        int ret=0;
        try{
            ret=apiService.updateApi(api);
        }catch (Exception e){
            ret=-1;
        }
        return ret;
    }

    /**
     * 插入
     * @param api
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insert")
    int insertApi(Api api){
        int ret=0;
        try{
            ret=apiService.insertApi(api);
        }catch (Exception e){
            ret=-1;
        }
        return ret;
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
