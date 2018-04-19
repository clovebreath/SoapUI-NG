package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestCase;
import io.mojohao.soapui_ng.service.TestCaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    List<TestCase> queryTestCaseByCondition(String caseId,String caseName,String caseLibId,String caseParaType){
        TestCase testCase=new TestCase();
        if(StringUtils.isNotBlank(caseId)&&StringUtils.isNumeric(caseId)){
            testCase.setCaseId(Integer.parseInt(caseId));
        }
        if(StringUtils.isNotBlank(caseLibId)&&StringUtils.isNumeric(caseLibId)){
            testCase.setCaseLibId(Integer.parseInt(caseLibId));
        }
        if(StringUtils.isNotBlank(caseName)){
            testCase.setCaseName(caseName);
        }
        if(StringUtils.isNotBlank(caseParaType)){
            testCase.setCaseParaType(caseParaType);
        }
        return testCaseService.queryTestCaseByCondition(testCase);
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
