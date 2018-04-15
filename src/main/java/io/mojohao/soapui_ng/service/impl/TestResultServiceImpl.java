package io.mojohao.soapui_ng.service.impl;

import io.mojohao.soapui_ng.entity.TestResult;
import io.mojohao.soapui_ng.service.TestResultService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestResultServiceImpl implements TestResultService {
    public List<TestResult> getAllTestResults() {
        return null;
    }

    public TestResult queryTestResultById(int testId, int caseId) {
        return null;
    }

    public List<TestResult> queryTestResultByCondition(TestResult testResult) {
        return null;
    }

    public int insertTestResult(TestResult testResult) {
        return 0;
    }
}
