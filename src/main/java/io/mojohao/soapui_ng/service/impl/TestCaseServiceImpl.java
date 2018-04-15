package io.mojohao.soapui_ng.service.impl;

import io.mojohao.soapui_ng.entity.TestCase;
import io.mojohao.soapui_ng.service.TestCaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseServiceImpl implements TestCaseService {
    public List<TestCase> getAllTestCases() {
        return null;
    }

    public TestCase queryTestCaseById(int id) {
        return null;
    }

    public List<TestCase> queryTestCaseByCondition(TestCase testCase) {
        return null;
    }

    public int deleteTestCaseById(int id) {
        return 0;
    }

    public int updateTestCase(TestCase testCase) {
        return 0;
    }

    public int insertTestCase(TestCase testCase) {
        return 0;
    }
}
