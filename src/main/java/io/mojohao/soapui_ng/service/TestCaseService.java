package io.mojohao.soapui_ng.service;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestCase;

import java.util.List;

public interface TestCaseService {
    List<TestCase> getAllTestCases();
    TestCase queryTestCaseById(int id);
    List<TestCase> queryTestCaseByCondition(TestCase testCase);
    int deleteTestCaseById(int id);
    int updateTestCase(TestCase testCase);
    int insertTestCase(TestCase testCase);
    List<ChartTypeDto> categoryByParaType();
    List<ChartTypeDto> categoryByLibId();
}
