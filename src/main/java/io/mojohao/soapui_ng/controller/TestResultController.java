package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.TestResult;
import io.mojohao.soapui_ng.service.TestResultService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/result")
public class TestResultController {
    @Autowired
    private TestResultService testResultService;

    @ResponseBody
    @RequestMapping(value = "/all")
    List<TestResult> getAllTestResults(){
        return testResultService.getAllTestResults();
    }
    TestResult queryTestResultById(@Param("testId") int testId, @Param("caseId") int caseId){
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/query")
    List<TestResult> queryTestResultByCondition(String caseId,String testId,String assertion){
        TestResult testResult=new TestResult();
        if(StringUtils.isNotBlank(caseId)&&StringUtils.isNumeric(caseId)){
            testResult.setCaseId(Integer.parseInt(caseId));
        }
        if(StringUtils.isNotBlank(testId)&&StringUtils.isNumeric(testId)){
            testResult.setTestId(Integer.parseInt(testId));
        }
        if(StringUtils.isNotBlank(assertion)){
            testResult.setAssertion(assertion);
        }
        return testResultService.queryTestResultByCondition(testResult);
    }

    @ResponseBody
    @RequestMapping(value = "/insert")
    int insertTestResult(TestResult testResult){
        return testResultService.insertTestResult(testResult);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    int deleteTestResult(@Param("testId") int testId, @Param("caseId") int caseId){
        return testResultService.deleteTestResult(testId,caseId);
    }
}
