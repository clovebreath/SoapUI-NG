package io.mojohao.soapui_ng.service.impl;

import io.mojohao.soapui_ng.dao.TestResultDao;
import io.mojohao.soapui_ng.entity.TestResult;
import io.mojohao.soapui_ng.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestResultServiceImpl implements TestResultService {

    @Autowired
    TestResultDao testResultDao;

    public List<TestResult> getAllTestResults() {
        return testResultDao.getAllTestResults();
    }

    public TestResult queryTestResultById(int testId, int caseId) {
        return null;
    }

    public List<TestResult> queryTestResultByCondition(TestResult testResult) {
        return testResultDao.queryTestResultByCondition(testResult);
    }

    public int insertTestResult(TestResult testResult) {
        return testResultDao.insertTestResult(testResult);
    }

    public int deleteTestResult(int testId, int caseId) {
        return testResultDao.deleteTestResult(testId,caseId);
    }
}
