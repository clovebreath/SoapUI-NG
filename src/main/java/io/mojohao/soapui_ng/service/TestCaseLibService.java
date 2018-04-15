package io.mojohao.soapui_ng.service;

import io.mojohao.soapui_ng.entity.TestCaseLib;

import java.util.List;

public interface TestCaseLibService {
    List<TestCaseLib> getAllTestCaseLibs();
    TestCaseLib queryTestCaseLibById(int id);
    List<TestCaseLib> queryTestCaseLibByCondition(TestCaseLib testCaseLib);
    int deleteTestCaseLibById(int id);
    int updateTestCaseLib(TestCaseLib testCaseLib);
    int insertTestCaseLib(TestCaseLib testCaseLib);
}
