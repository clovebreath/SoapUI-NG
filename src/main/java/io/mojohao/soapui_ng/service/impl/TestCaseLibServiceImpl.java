package io.mojohao.soapui_ng.service.impl;

import io.mojohao.soapui_ng.dao.TestCaseLibDao;
import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestCaseLib;
import io.mojohao.soapui_ng.service.TestCaseLibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseLibServiceImpl implements TestCaseLibService {
    @Autowired
    private TestCaseLibDao testCaseLibDao;

    public List<TestCaseLib> getAllTestCaseLibs() {
        return testCaseLibDao.getAllTestCaseLibs();
    }

    public TestCaseLib queryTestCaseLibById(int id) {
        return testCaseLibDao.queryTestCaseLibById(id);
    }

    public List<TestCaseLib> queryTestCaseLibByCondition(TestCaseLib testCaseLib) {
        return testCaseLibDao.queryTestCaseLibByCondition(testCaseLib);
    }

    public int deleteTestCaseLibById(int id) {
        return testCaseLibDao.deleteTestCaseLibById(id);
    }

    public int updateTestCaseLib(TestCaseLib testCaseLib) {
        return testCaseLibDao.updateTestCaseLib(testCaseLib);
    }

    public int insertTestCaseLib(TestCaseLib testCaseLib) {
        return testCaseLibDao.insertTestCaseLib(testCaseLib);
    }

    public List<ChartTypeDto> categoryByApplyApiId(){
        return testCaseLibDao.categoryByApplyApiId();
    }

}
