package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.TestCaseLib;
import io.mojohao.soapui_ng.service.TestCaseLibService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/testCaseLib")
public class TestCaseLibController {
    @Autowired
    private TestCaseLibService testCaseLibService;

    @ResponseBody
    @RequestMapping(value = "/all")
    List<TestCaseLib> getAllTestCaseLibs(){
        return testCaseLibService.getAllTestCaseLibs();
    }

    TestCaseLib queryTestCaseLibById(int id){
        return testCaseLibService.queryTestCaseLibById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/query")
    List<TestCaseLib> queryTestCaseLibByCondition(String libId,String libName,String applyApiId){
        TestCaseLib caseLib=new TestCaseLib();
        if(StringUtils.isNotBlank(libId)&&StringUtils.isNumeric(libId)){
            caseLib.setLibId(Integer.parseInt(libId));
        }
        if(StringUtils.isNotBlank(libName)){
            caseLib.setLibName(libName);
        }
        if(StringUtils.isNotBlank(applyApiId)&&StringUtils.isNumeric(applyApiId)){
            caseLib.setApplyApiId(Integer.parseInt(applyApiId));
        }
        return testCaseLibService.queryTestCaseLibByCondition(caseLib);
    }
    @ResponseBody
    @RequestMapping(value = "/delete")
    int deleteTestCaseLibById(int id){
        return testCaseLibService.deleteTestCaseLibById(id);
    }
    @ResponseBody
    @RequestMapping(value = "/update")
    int updateTestCaseLib(TestCaseLib testCaseLib){
        return testCaseLibService.updateTestCaseLib(testCaseLib);
    }
    @ResponseBody
    @RequestMapping(value = "/insert")
    int insertTestCaseLib(TestCaseLib testCaseLib){
        return testCaseLibService.insertTestCaseLib(testCaseLib);
    }
}
