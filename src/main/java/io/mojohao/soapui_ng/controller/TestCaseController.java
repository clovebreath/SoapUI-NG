package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.TestCase;
import io.mojohao.soapui_ng.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/testCase")
public class TestCaseController {
    @Autowired
    private TestCaseService testCaseService;

    List<TestCase> getAllTestCases(){
        return null;
    }
    TestCase queryTestCaseById(int id){
        return null;
    }
    List<TestCase> queryTestCaseByCondition(TestCase testCase){
        return null;
    }
    int deleteTestCaseById(int id){
        return id;
    }
    int updateTestCase(TestCase testCase){
        return 0;
    }
    int insertTestCase(TestCase testCase){
        return 0;
    }
}
