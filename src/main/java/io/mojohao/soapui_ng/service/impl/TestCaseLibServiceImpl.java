package io.mojohao.soapui_ng.service.impl;

import io.mojohao.soapui_ng.dao.TestCaseLibDao;
import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestCaseLib;
import io.mojohao.soapui_ng.service.TestCaseLibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestCaseLibServiceImpl implements TestCaseLibService {
    @Autowired
    private TestCaseLibDao testCaseLibDao;

    @Override
    public List<TestCaseLib> getAllTestCaseLibs() {
        return testCaseLibDao.getAllTestCaseLibs();
    }

    @Override
    public TestCaseLib queryTestCaseLibById(int id) {
        return testCaseLibDao.queryTestCaseLibById(id);
    }

    @Override
    public List<TestCaseLib> queryTestCaseLibByCondition(TestCaseLib testCaseLib) {
        return testCaseLibDao.queryTestCaseLibByCondition(testCaseLib);
    }

    @Override
    public List<TestCaseLib> queryTestCaseLibByPage(Map param) {
        return testCaseLibDao.queryTestCaseLibByPage(param);
    }

    @Override
    public int deleteTestCaseLibById(int id) {
        return testCaseLibDao.deleteTestCaseLibById(id);
    }

    @Override
    public int queryAmount(Map param) {
        return testCaseLibDao.queryAmount(param);
    }

    @Override
    public int updateTestCaseLib(TestCaseLib testCaseLib) {
        return testCaseLibDao.updateTestCaseLib(testCaseLib);
    }

    @Override
    public int insertTestCaseLib(TestCaseLib testCaseLib) {
        return testCaseLibDao.insertTestCaseLib(testCaseLib);
    }

    @Override
    public List<ChartTypeDto> categoryByApplyApiId(){
        return testCaseLibDao.categoryByApplyApiId();
    }

}
