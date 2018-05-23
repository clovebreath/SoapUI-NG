package io.mojohao.soapui_ng.service.impl;

import com.google.gson.JsonParser;
import io.mojohao.soapui_ng.dao.TestPlanDao;
import io.mojohao.soapui_ng.dao.TestResultDao;
import io.mojohao.soapui_ng.entity.*;
import io.mojohao.soapui_ng.service.TestPlanService;
import io.mojohao.soapui_ng.util.WebServiceConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.Timestamp;
import java.util.*;

@Service
public class TestPlanServiceImpl implements TestPlanService {
    @Autowired
    private TestPlanDao testPlanDao;

    @Autowired
    private TestResultDao testResultDao;

    @Autowired
    private WebServiceConsumer serviceConsumer;

    @Override
    public List<TestPlan> getAllTestPlans() {
        return testPlanDao.getAllTestPlans();
    }

    @Override
    public TestPlan queryTestPlanById(int id) {
        return testPlanDao.queryTestPlanById(id);
    }

    @Override
    public List<TestPlan> queryTestPlanByCondition(TestPlan testPlan) {
        return testPlanDao.queryTestPlanByCondition(testPlan);
    }

    @Override
    public List<TestPlan> queryTestPlanByPage(Map map) {
        return testPlanDao.queryTestPlanByPage(map);
    }

    @Override
    public int deleteTestPlanById(int planId) {
        return testPlanDao.deleteTestPlanById(planId);
    }

    @Override
    public int updateTestPlan(TestPlan plan) {
        return testPlanDao.updateTestPlan(plan);
    }

    @Override
    public int updateTestPlanStatus(TestPlan plan) {
        return testPlanDao.updateTestPlanStatus(plan);
    }

    @Override
    public int insertTestPlan(TestPlan plan) {
        int ret=testPlanDao.insertTestPlan(plan);
        if(0!=ret){
            ret=plan.getTestPlanId();
        }
        return ret;
    }

    @Override
    public List<ChartTypeDto> categoryByLibId() {
        return testPlanDao.categoryByLibId();
    }

    @Override
    public List<ChartTypeDto> categoryByApiId() {
        return testPlanDao.categoryByApiId();
    }

    @Override
    public List<ChartTypeDto> categoryByStatus() {
        return testPlanDao.categoryByStatus();
    }
    @Override
    public int countAll() {
        return testPlanDao.countAll();
    }

    @Override
    public int queryAmount(Map param) {
        return testPlanDao.queryAmount(param);
    }

    @Override
    public int excutePlan(Api api, List<TestCase> caseList, TestPlan plan) {
        //设置当前计划执行状态 1-执行中
        plan.setPlanStatus(1);
        testPlanDao.updateTestPlanStatus(plan);
        //生成测试编号
        String testId=UUID.randomUUID().toString();
        //新建测试结果对象
        TestResult result=new TestResult();
        result.setTestId(testId);
        result.setTestPlanId(plan.getTestPlanId());

        //rest接口
        if("REST".equals(api.getApiType())){
            for (TestCase testCase :
                    caseList) {
                //设置相应的数据
                result.setCaseId(testCase.getCaseId());
                result.setTestDate(new Timestamp(System.currentTimeMillis()));
                result.setDesiredResponse(testCase.getDesiredResponse());
                //接口访问方式
                HttpMethod accessMethod=HttpMethod.HEAD;
                if("GET".equals(api.getAccessMode())){
                    accessMethod=HttpMethod.GET;
                }else if("PUT".equals(api.getAccessMode())){
                    accessMethod=HttpMethod.PUT;
                }else if("POST".equals(api.getAccessMode())){
                    accessMethod=HttpMethod.POST;
                }else if("DELETE".equals(api.getAccessMode())){
                    accessMethod=HttpMethod.DELETE;
                }
                Map responseMap;
                try {
                    //获取接口的返回值
                    responseMap = serviceConsumer.consumeRestService(api.getApiLink(),testCase.getParameter(),accessMethod,new HashMap());
                } catch (HttpClientErrorException e) {
                    responseMap=new HashMap();
                    responseMap.put("responseCode",e.getStatusCode());
                }
                if(200==(int)responseMap.get("responseCode")){
                    String acturalResponse =(String) responseMap.get("response");
                    result.setActualResponse(acturalResponse);
                    if(serviceConsumer.compareJson(testCase.getDesiredResponse(),acturalResponse)){
                        result.setAssertion("PASSED");
                    }else{
                        result.setAssertion("UNPASSED");
                    }
                }else{
                    result.setActualResponse("BAD RETURN CODE:"+(int)responseMap.get("responseCode"));
                    result.setAssertion("UNPASSED");
                }
                testResultDao.insertTestResult(result);
            }
        }

        //soap接口
        if("SOAP".equals(api.getApiType())){
            for (TestCase testCase :
                    caseList) {

                    //设置相应的数据
                    result.setCaseId(testCase.getCaseId());
                    result.setTestDate(new Timestamp(System.currentTimeMillis()));
                    result.setDesiredResponse(testCase.getDesiredResponse());
                HashMap responseMap;
                try {
                    //获取接口的返回值
                    responseMap = serviceConsumer.consumeSoapService(api.getApiLink(),testCase.getParameter());
                } catch (Exception e) {
                    responseMap=new HashMap();
                    if(e.getClass().equals(HttpClientErrorException.class)){
                        responseMap.put("responseCode",((HttpClientErrorException)e).getStatusCode());
                    }else {
                        responseMap.put("responseCode","500");
                    }
                }
                if(200==(int)responseMap.get("responseCode")){
                    String acturalResponse =(String) responseMap.get("response");
                    result.setActualResponse(acturalResponse);
                    if(serviceConsumer.compareXml(testCase.getDesiredResponse(),acturalResponse)){
                        result.setAssertion("PASSED");
                    }else{
                        result.setAssertion("UNPASSED");
                    }
                }else{
                    result.setActualResponse("BAD RETURN CODE:"+(int)responseMap.get("responseCode"));
                    result.setAssertion("UNPASSED");
                }
                testResultDao.insertTestResult(result);
            }
        }
        //设置当前计划执行状态 2-执行完成
        plan.setPlanStatus(2);
        testPlanDao.updateTestPlanStatus(plan);
        return 2;
    }
}
