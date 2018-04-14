package io.mojohao.soapui_ng.dao;

import io.mojohao.soapui_ng.entity.TestResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestResultDao {
    List<TestResult> getAllTestResults();
    TestResult queryTestResultById(@Param("testId") int testId,@Param("caseId") int caseId);
    List<TestResult> queryTestResultByCondition(TestResult testResult);
    int insertTestResult(TestResult testResult);
}
