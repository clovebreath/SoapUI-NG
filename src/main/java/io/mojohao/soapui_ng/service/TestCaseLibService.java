package io.mojohao.soapui_ng.service;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestCaseLib;

import java.util.List;
import java.util.Map;

public interface TestCaseLibService {
    List<TestCaseLib> getAllTestCaseLibs();
    TestCaseLib queryTestCaseLibById(int id);
    List<TestCaseLib> queryTestCaseLibByCondition(TestCaseLib testCaseLib);
    List<TestCaseLib> queryTestCaseLibByPage(Map param);
    int deleteTestCaseLibById(int id);
    int queryAmount(Map param);
    int updateTestCaseLib(TestCaseLib testCaseLib);
    int insertTestCaseLib(TestCaseLib testCaseLib);
    List<ChartTypeDto> categoryByApplyApiId();
}
