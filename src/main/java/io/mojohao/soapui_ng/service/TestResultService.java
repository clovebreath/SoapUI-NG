package io.mojohao.soapui_ng.service;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TestResultService {
    List<TestResult> getAllTestResults();
    TestResult queryTestResultById(int testId);
    List<TestResult> queryTestResultByCondition(TestResult testResult);
    List<TestResult> queryTestResultByPage(Map param);
    int insertTestResult(TestResult testResult);
    int deleteTestResult(int testId);
    List<ChartTypeDto> categoryByTestPlanId();
    List<ChartTypeDto> categoryByCaseId();
    List<ChartTypeDto> categoryByAssertion();
    /**
     * 统计符合条件的多少个
     * @return
     */
    int queryAmount(Map param);
}
