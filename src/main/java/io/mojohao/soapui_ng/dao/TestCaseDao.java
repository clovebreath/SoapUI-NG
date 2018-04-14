package io.mojohao.soapui_ng.dao;

import io.mojohao.soapui_ng.entity.TestCase;

import java.util.List;

public interface TestCaseDao {
    List<TestCase> getAllTestCases();
    TestCase queryTestCaseById(int id);
    List<TestCase> queryTestCaseByCondition(TestCase testCase);
    int deleteTestCaseById(int id);
    int updateTestCase(TestCase testCase);
    int insertTestCase(TestCase testCase);
}
