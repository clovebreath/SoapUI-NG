package io.mojohao.soapui_ng.dao;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestCaseLib;

import java.util.List;

public interface TestCaseLibDao {
    List<TestCaseLib> getAllTestCaseLibs();
    TestCaseLib queryTestCaseLibById(int id);
    List<TestCaseLib> queryTestCaseLibByCondition(TestCaseLib testCaseLib);
    int deleteTestCaseLibById(int id);
    int updateTestCaseLib(TestCaseLib testCaseLib);
    int insertTestCaseLib(TestCaseLib testCaseLib);
    List<ChartTypeDto> categoryByApplyApiId();
    int countAll();
}
