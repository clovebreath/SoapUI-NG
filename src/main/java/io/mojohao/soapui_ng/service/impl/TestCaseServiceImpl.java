package io.mojohao.soapui_ng.service.impl;

import io.mojohao.soapui_ng.dao.TestCaseDao;
import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestCase;
import io.mojohao.soapui_ng.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestCaseServiceImpl implements TestCaseService {

    @Autowired
    private TestCaseDao testCaseDao;

    @Override
    public List<TestCase> getAllTestCases() {
        return testCaseDao.getAllTestCases();
    }

    @Override
    public TestCase queryTestCaseById(int id) {
        return testCaseDao.queryTestCaseById(id);
    }

    @Override
    public List<TestCase> queryTestCaseByCondition(TestCase testCase) {
        return testCaseDao.queryTestCaseByCondition(testCase);
    }

    @Override
    public List<TestCase> queryTestCaseByPage(Map param) {
        return testCaseDao.queryTestCaseByPage(param);
    }
    @Override
    public int deleteTestCaseById(int id) {
        return testCaseDao.deleteTestCaseById(id);
    }

    @Override
    public int queryAmount(Map param) {
        return testCaseDao.queryAmount(param);
    }

    @Override
    public int updateTestCase(TestCase testCase) {
        return testCaseDao.updateTestCase(testCase);
    }

    @Override
    public int insertTestCase(TestCase testCase) {
        return testCaseDao.insertTestCase(testCase);
    }

    @Override
    public List<ChartTypeDto> categoryByParaType() {
        return testCaseDao.categoryByParaType();
    }

    @Override
    public List<ChartTypeDto> categoryByLibId() {
        return testCaseDao.categoryByLibId();
    }
}
