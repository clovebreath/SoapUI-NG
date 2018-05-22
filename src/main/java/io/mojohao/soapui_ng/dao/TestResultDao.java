package io.mojohao.soapui_ng.dao;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TestResultDao {
    List<TestResult> getAllTestResults();
    TestResult queryTestResultById(@Param("resultId") int resultId);
    List<TestResult> queryTestResultByCondition(TestResult testResult);
    List<TestResult> queryTestResultByPage(Map param);
    int insertTestResult(TestResult testResult);
    int deleteTestResult(@Param("resultId") int resultId);
    List<ChartTypeDto> categoryByTestPlanId();
    List<ChartTypeDto> categoryByCaseId();
    List<ChartTypeDto> categoryByAssertion();
    int countAll();
    /**
     * 统计符合条件的多少个
     * @return
     */
    int queryAmount(Map param);
}
