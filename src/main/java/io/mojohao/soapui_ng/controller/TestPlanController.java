package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.*;
import io.mojohao.soapui_ng.service.ApiService;
import io.mojohao.soapui_ng.service.TestCaseLibService;
import io.mojohao.soapui_ng.service.TestCaseService;
import io.mojohao.soapui_ng.service.TestPlanService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/plan")
public class TestPlanController {
    @Autowired
    private TestPlanService planService;
    @Autowired
    TestCaseService testCaseService;
    @Autowired
    ApiService apiService;

    @ResponseBody
    @RequestMapping(value = "/all")
    public List<TestPlan> getAllPlans(){
        return planService.getAllTestPlans();
    }

    @ResponseBody
    @RequestMapping(value = "/query")
    public HashMap queryPlanByPage(String planId,String libId,String apiId,String planStatus,int currPage ){
        Map param=new HashMap<String,Object>();
        HashMap<String,Object> retMap=new HashMap<>();
        if(StringUtils.isNotBlank(planId)&&StringUtils.isNumeric(planId)){
            param.put("testPlanId",Integer.parseInt(planId));
        }
        if(StringUtils.isNotBlank(libId)&&StringUtils.isNumeric(libId)){
            param.put("libId",Integer.parseInt(libId));
        }
        if(StringUtils.isNotBlank(apiId)&&StringUtils.isNumeric(apiId)){
            param.put("apiId",Integer.parseInt(apiId));
        }
        if(StringUtils.isNotBlank(planStatus)&&StringUtils.isNumeric(planStatus)){
            param.put("planStatus",Integer.parseInt(planStatus));
        }
        if(currPage<=0) {
            currPage = 1;
        }
        param.put("currPage",currPage);
        retMap.put("list",planService.queryTestPlanByPage(param));
        retMap.put("total",planService.queryAmount(param));
        return retMap;
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    int deletePlanById(int planId){
        return planService.deleteTestPlanById(planId);
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    int updatePlan(TestPlan plan){
        return planService.updateTestPlan(plan);
    }

    @ResponseBody
    @RequestMapping(value = "/insert")
    int insertPlan(TestPlan plan){
        return planService.insertTestPlan(plan);
    }

    @ResponseBody
    @RequestMapping(value = "/categoryByApi")
    public List<ChartTypeDto> categoryByApiId() {
        return planService.categoryByApiId();
    }

    @ResponseBody
    @RequestMapping(value = "/categoryByStatus")
    public List<ChartTypeDto> categoryByStatus() {
        return planService.categoryByStatus();
    }

    @ResponseBody
    @RequestMapping(value = "/execute")
    int executePlan(int planId){
        synchronized (this){
            TestPlan testPlan=planService.queryTestPlanById(planId);
            Api api=apiService.queryApiById(testPlan.getApiId());
            TestCase queryCase=new TestCase();
            queryCase.setCaseLibId(testPlan.getLibId());
            List<TestCase> caseList=testCaseService.queryTestCaseByCondition(queryCase);
            planService.excutePlan(api,caseList,testPlan);
        }
        return 0;
    }
}
