package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestCaseLib;
import io.mojohao.soapui_ng.service.TestCaseLibService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/testCaseLib")
public class TestCaseLibController {
    @Autowired
    private TestCaseLibService testCaseLibService;

    @ResponseBody
    @RequestMapping(value = "/all")
    List<TestCaseLib> getAllTestCaseLibs(){
        return testCaseLibService.getAllTestCaseLibs();
    }

    TestCaseLib queryTestCaseLibById(int id){
        return testCaseLibService.queryTestCaseLibById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/query")
    HashMap<String,Object> queryTestCaseLibByCondition(String libId,String libName,String applyApiId,int currPage){
        Map param=new HashMap<String,Object>();
        HashMap<String,Object> retMap=new HashMap<>();
        if(StringUtils.isNotBlank(libId)&&StringUtils.isNumeric(libId)){
            param.put("libId",Integer.parseInt(libId));
        }
        if(StringUtils.isNotBlank(libName)){
            param.put("libName",libName);
        }
        if(StringUtils.isNotBlank(applyApiId)&&StringUtils.isNumeric(applyApiId)){
            param.put("applyApiId",Integer.parseInt(applyApiId));
        }
        if(currPage<=0) {
            currPage = 1;
        }
        param.put("currPage",currPage);
        retMap.put("list",testCaseLibService.queryTestCaseLibByPage(param));
        retMap.put("total",testCaseLibService.queryAmount(param));
        return retMap;
    }
    @ResponseBody
    @RequestMapping(value = "/delete")
    int deleteTestCaseLibById(int id){
        return testCaseLibService.deleteTestCaseLibById(id);
    }
    @ResponseBody
    @RequestMapping(value = "/update")
    int updateTestCaseLib(TestCaseLib testCaseLib){
        return testCaseLibService.updateTestCaseLib(testCaseLib);
    }
    @ResponseBody
    @RequestMapping(value = "/insert")
    int insertTestCaseLib(TestCaseLib testCaseLib){
        return testCaseLibService.insertTestCaseLib(testCaseLib);
    }
    @ResponseBody
    @RequestMapping(value = "/categoryByApplyApiId")
    public List<ChartTypeDto> categoryByApplyApiId(){
        return testCaseLibService.categoryByApplyApiId();
    }
}
