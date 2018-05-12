package io.mojohao.soapui_ng.service.impl;

import io.mojohao.soapui_ng.dao.TestResultDao;
import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestResult;
import io.mojohao.soapui_ng.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestResultServiceImpl implements TestResultService {

    @Autowired
    TestResultDao testResultDao;

    @Override
    public List<TestResult> getAllTestResults() {
        return testResultDao.getAllTestResults();
    }

    @Override
    public TestResult queryTestResultById(int testId) {
        return testResultDao.queryTestResultById(testId);
    }

    @Override
    public List<TestResult> queryTestResultByCondition(TestResult testResult) {
        return testResultDao.queryTestResultByCondition(testResult);
    }
    @Override
    public List<TestResult> queryTestResultByPage(Map param) {
        return testResultDao.queryTestResultByPage(param);
    }
    @Override
    public int insertTestResult(TestResult testResult) {
        return testResultDao.insertTestResult(testResult);
    }

    @Override
    public int deleteTestResult(int testId) {
        return testResultDao.deleteTestResult(testId);
    }

    @Override
    public List<ChartTypeDto> categoryByTestPlanId() {
        return testResultDao.categoryByTestPlanId();
    }

    @Override
    public List<ChartTypeDto> categoryByCaseId() {
        return testResultDao.categoryByCaseId();
    }

    @Override
    public List<ChartTypeDto> categoryByAssertion() {
        return testResultDao.categoryByAssertion();
    }
    @Override
    public int queryAmount(Map param) {
        return testResultDao.queryAmount(param);
    }
}
