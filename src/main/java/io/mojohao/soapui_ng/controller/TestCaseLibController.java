package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.TestCaseLib;
import io.mojohao.soapui_ng.service.TestCaseLibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/testCaseLib")
public class TestCaseLibController {
    @Autowired
    private TestCaseLibService testCaseLibService;

    List<TestCaseLib> getAllTestCaseLibs(){
        return null;
    }
    TestCaseLib queryTestCaseLibById(int id){
        return null;
    }
    List<TestCaseLib> queryTestCaseLibByCondition(TestCaseLib testCaseLib){
        return null;
    }
    int deleteTestCaseLibById(int id){
        return id;
    }
    int updateTestCaseLib(TestCaseLib testCaseLib){
        return 0;
    }
    int insertTestCaseLib(TestCaseLib testCaseLib){
        return 0;
    }
}
