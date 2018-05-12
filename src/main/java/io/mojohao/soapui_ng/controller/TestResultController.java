package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestResult;
import io.mojohao.soapui_ng.service.TestResultService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    TestResult queryTestResultById(@Param("testId") int testId){
        return testResultService.queryTestResultById(testId);
    }

    @ResponseBody
    @RequestMapping(value = "/query")
    HashMap<String,Object> queryTestResultByCondition(String caseId,String testId,String testPlanId,String assertion,int currPage){
        Map param=new HashMap<String,Object>();
        if(StringUtils.isNotBlank(caseId)&&StringUtils.isNumeric(caseId)){
            param.put("caseId",Integer.parseInt(caseId));
        }
        if(StringUtils.isNotBlank(testPlanId)&&StringUtils.isNumeric(testPlanId)){
            param.put("testPlanId",Integer.parseInt(testPlanId));
        }
        if(StringUtils.isNotBlank(testId)&&StringUtils.isNumeric(testId)){
            param.put("testId",Integer.parseInt(testId));
        }
        if(StringUtils.isNotBlank(assertion)){
            param.put("assertion",assertion);
        }
        if(currPage<=0) {
            currPage = 1;
        }
        param.put("currPage",currPage);
        HashMap<String,Object> retMap=new HashMap<>();
        retMap.put("list",testResultService.queryTestResultByPage(param));
        retMap.put("total",testResultService.queryAmount(param));
        return retMap;
    }

    @ResponseBody
    @RequestMapping(value = "/insert")
    int insertTestResult(String testPlanId,String caseId,String testDate,String desiredResponse,String actualResponse,String assertion) throws ParseException {
        TestResult testResult=new TestResult();
        if(StringUtils.isNotBlank(caseId)&&StringUtils.isNumeric(caseId)){
            testResult.setCaseId(Integer.parseInt(caseId));
        }
        if(StringUtils.isNotBlank(testPlanId)&&StringUtils.isNumeric(testPlanId)){
            testResult.setTestId(Integer.parseInt(testPlanId));
        }
        if(StringUtils.isNotBlank(assertion)){
            testResult.setAssertion(assertion);
        }
        if(StringUtils.isNotBlank(desiredResponse)){
            testResult.setDesiredResponse(desiredResponse);
        }
        if(StringUtils.isNotBlank(actualResponse)){
            testResult.setActualResponse(actualResponse);
        }
        if(StringUtils.isNotBlank(testDate)){
            testResult.setTestDate(convertDateTime(testDate.replace("T"," ")));
        }
        return testResultService.insertTestResult(testResult);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    int deleteTestResult(@Param("testId") int testId){
        return testResultService.deleteTestResult(testId);
    }
    @ResponseBody
    @RequestMapping(value = "/categoryByTestPlanId")
    List<ChartTypeDto> categoryByTestPlanId(){
        return testResultService.categoryByTestPlanId();
    }
    @ResponseBody
    @RequestMapping(value = "/categoryByCaseId")
    List<ChartTypeDto> categoryByCaseId(){
        return testResultService.categoryByCaseId();
    }

    @ResponseBody
    @RequestMapping(value = "/categoryByAssertion")
    List<ChartTypeDto> categoryByAssertion(){
        return testResultService.categoryByAssertion();
    }


    private Timestamp convertDateTime(String dateTime) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        format.setLenient(false);
        return new Timestamp(format.parse(dateTime).getTime());

    }
}
