package io.mojohao.soapui_ng.dao;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestCaseLib;

import java.util.List;
import java.util.Map;

public interface TestCaseLibDao {
    List<TestCaseLib> getAllTestCaseLibs();
    TestCaseLib queryTestCaseLibById(int id);
    List<TestCaseLib> queryTestCaseLibByCondition(TestCaseLib testCaseLib);
    List<TestCaseLib> queryTestCaseLibByPage(Map param);
    int deleteTestCaseLibById(int id);
    int updateTestCaseLib(TestCaseLib testCaseLib);
    int insertTestCaseLib(TestCaseLib testCaseLib);
    List<ChartTypeDto> categoryByApplyApiId();
    int countAll();
    int queryAmount(Map param);
}
