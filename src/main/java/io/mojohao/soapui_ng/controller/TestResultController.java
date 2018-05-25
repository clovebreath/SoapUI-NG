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

    /**
     * 获取全部
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/all")
    List<TestResult> getAllTestResults(){
        return testResultService.getAllTestResults();
    }

    /**
     * 获取一个
     * @param resultId
     * @return
     */
    TestResult queryTestResultById(@Param("resultId") int resultId){
        return testResultService.queryTestResultById(resultId);
    }

    /**
     * 分页查询
     * @param caseId
     * @param testId
     * @param testPlanId
     * @param assertion
     * @param currPage
     * @return
     */
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
        if(StringUtils.isNotBlank(testId)){
            param.put("testId",testId);
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

    /**
     * 插入
     * @param testPlanId
     * @param testId
     * @param caseId
     * @param testDate
     * @param desiredResponse
     * @param actualResponse
     * @param assertion
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping(value = "/insert")
    int insertTestResult(String testPlanId,String testId,String caseId,String testDate,String desiredResponse,String actualResponse,String assertion) throws ParseException {
        TestResult testResult=new TestResult();
        if(StringUtils.isNotBlank(caseId)&&StringUtils.isNumeric(caseId)){
            testResult.setCaseId(Integer.parseInt(caseId));
        }
        if(StringUtils.isNotBlank(testPlanId)&&StringUtils.isNumeric(testPlanId)){
            testResult.setTestPlanId(Integer.parseInt(testPlanId));
        }
        if(StringUtils.isNotBlank(testId)){
            testResult.setTestId(testId);
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

    /**
     * 删除
     * @param resultId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    int deleteTestResult(@Param("resultId") int resultId){
        return testResultService.deleteTestResult(resultId);
    }

    /**
     * 根据测试计划 ID统计
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/categoryByTestPlanId")
    List<ChartTypeDto> categoryByTestPlanId(){
        return testResultService.categoryByTestPlanId();
    }

    /**
     * 根据用例统计
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/categoryByCaseId")
    List<ChartTypeDto> categoryByCaseId(){
        return testResultService.categoryByCaseId();
    }

    /**
     * 根据测试结果统计
     * @param testId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/categoryByAssertion")
    List<ChartTypeDto> categoryByAssertion(String testId){
        return testResultService.categoryByAssertion(testId);
    }

    /**
     * 转换时间格式
     * @param dateTime
     * @return
     * @throws ParseException
     */
    private Timestamp convertDateTime(String dateTime) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        format.setLenient(false);
        return new Timestamp(format.parse(dateTime).getTime());

    }
}
