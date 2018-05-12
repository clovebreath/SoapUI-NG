package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestCase;
import io.mojohao.soapui_ng.service.TestCaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/testCase")
public class TestCaseController {
    @Autowired
    private TestCaseService testCaseService;

    @ResponseBody
    @RequestMapping(value = "/all")
    List<TestCase> getAllTestCases(){
        return testCaseService.getAllTestCases();
    }
    TestCase queryTestCaseById(int id){
        return testCaseService.queryTestCaseById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/query")
    HashMap<String,Object> queryTestCaseByCondition(String caseId,String caseName,String caseLibId,String caseParaType,int currPage){
        Map param=new HashMap<String,Object>();
        HashMap<String,Object> retMap=new HashMap<>();
        if(StringUtils.isNotBlank(caseId)&&StringUtils.isNumeric(caseId)){
            param.put("caseId",Integer.parseInt(caseId));
        }
        if(StringUtils.isNotBlank(caseLibId)&&StringUtils.isNumeric(caseLibId)){
            param.put("caseLibId",Integer.parseInt(caseLibId));
        }
        if(StringUtils.isNotBlank(caseName)){
            param.put("caseName",caseName);
        }
        if(StringUtils.isNotBlank(caseParaType)){
            param.put("caseParaType",caseParaType);
        }
        if(currPage<=0) {
            currPage = 1;
        }
        param.put("currPage",currPage);
        retMap.put("list",testCaseService.queryTestCaseByPage(param));
        retMap.put("total",testCaseService.queryAmount(param));
        return retMap;
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    int deleteTestCaseById(int id){
        return testCaseService.deleteTestCaseById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    int updateTestCase(TestCase testCase){
        return testCaseService.updateTestCase(testCase);
    }

    @ResponseBody
    @RequestMapping(value = "/insert")
    int insertTestCase(TestCase testCase){
        return testCaseService.insertTestCase(testCase);
    }

    @ResponseBody
    @RequestMapping(value = "/categoryByParaType")
    List<ChartTypeDto> categoryByParaType(){
        return testCaseService.categoryByParaType();
    }

    @ResponseBody
    @RequestMapping(value = "/categoryByLibId")
    List<ChartTypeDto> categoryByLibId(){
        return testCaseService.categoryByLibId();
    }
}
