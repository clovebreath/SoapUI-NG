package io.mojohao.soapui_ng.service.impl;

import io.mojohao.soapui_ng.dao.TestCaseDao;
import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestCase;
import io.mojohao.soapui_ng.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseServiceImpl implements TestCaseService {

    @Autowired
    private TestCaseDao testCaseDao;

    public List<TestCase> getAllTestCases() {
        return testCaseDao.getAllTestCases();
    }

    public TestCase queryTestCaseById(int id) {
        return testCaseDao.queryTestCaseById(id);
    }

    public List<TestCase> queryTestCaseByCondition(TestCase testCase) {
        return testCaseDao.queryTestCaseByCondition(testCase);
    }

    public int deleteTestCaseById(int id) {
        return testCaseDao.deleteTestCaseById(id);
    }

    public int updateTestCase(TestCase testCase) {
        return testCaseDao.updateTestCase(testCase);
    }

    public int insertTestCase(TestCase testCase) {
        return testCaseDao.insertTestCase(testCase);
    }

    public List<ChartTypeDto> categoryByParaType() {
        return testCaseDao.categoryByParaType();
    }

    public List<ChartTypeDto> categoryByLibId() {
        return testCaseDao.categoryByLibId();
    }
}
