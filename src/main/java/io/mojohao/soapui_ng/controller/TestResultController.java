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
    int insertTestResult(String testId,String caseId,String testDate,String desiredResponse,String actualResponse,String assertion) throws ParseException {
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
    int deleteTestResult(@Param("testId") int testId, @Param("caseId") int caseId){
        return testResultService.deleteTestResult(testId,caseId);
    }
    @ResponseBody
    @RequestMapping(value = "/categoryByTestId")
    List<ChartTypeDto> categoryByTestId(){
        return testResultService.categoryByTestId();
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
