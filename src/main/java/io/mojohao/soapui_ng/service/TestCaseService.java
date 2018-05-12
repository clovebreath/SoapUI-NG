package io.mojohao.soapui_ng.service;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestCase;

import java.util.List;
import java.util.Map;

public interface TestCaseService {
    List<TestCase> getAllTestCases();
    TestCase queryTestCaseById(int id);
    List<TestCase> queryTestCaseByCondition(TestCase testCase);
    List<TestCase> queryTestCaseByPage(Map param);
    int deleteTestCaseById(int id);
    int queryAmount(Map param);
    int updateTestCase(TestCase testCase);
    int insertTestCase(TestCase testCase);
    List<ChartTypeDto> categoryByParaType();
    List<ChartTypeDto> categoryByLibId();
}
