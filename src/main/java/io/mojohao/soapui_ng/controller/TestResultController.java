package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.TestResult;
import io.mojohao.soapui_ng.service.TestResultService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/testResult")
public class TestResultController {
    @Autowired
    private TestResultService testResultService;

    List<TestResult> getAllTestResults(){
        return null;
    }
    TestResult queryTestResultById(@Param("testId") int testId, @Param("caseId") int caseId){
        return null;
    }
    List<TestResult> queryTestResultByCondition(TestResult testResult){
        return null;
    }
    int insertTestResult(TestResult testResult){
        return 0;
    }
}
