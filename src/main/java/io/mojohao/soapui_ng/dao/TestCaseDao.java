package io.mojohao.soapui_ng.dao;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestCase;

import java.util.List;
import java.util.Map;

public interface TestCaseDao {
    List<TestCase> getAllTestCases();
    TestCase queryTestCaseById(int id);
    List<TestCase> queryTestCaseByCondition(TestCase testCase);
    List<TestCase> queryTestCaseByPage(Map param);
    int deleteTestCaseById(int id);
    int updateTestCase(TestCase testCase);
    int insertTestCase(TestCase testCase);
    List<ChartTypeDto> categoryByParaType();
    List<ChartTypeDto> categoryByLibId();
    int countAll();
    int queryAmount(Map param);

}
