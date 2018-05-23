package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestCase;
import io.mojohao.soapui_ng.service.TestCaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/testCase")
public class TestCaseController {
    @Autowired
    private TestCaseService testCaseService;

    /**
     * 查询所有
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/all")
    List<TestCase> getAllTestCases(){
        return testCaseService.getAllTestCases();
    }

    /**
     * 查询 根据主键
     * @param id
     * @return
     */
    TestCase queryTestCaseById(int id){
        return testCaseService.queryTestCaseById(id);
    }

    /**
     * 查询 根据条件
     * @param caseId
     * @param caseName
     * @param caseLibId
     * @param caseParaType
     * @param currPage
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query")
    HashMap<String,Object> queryTestCaseByCondition(String caseId,String caseName,String caseLibId,String caseParaType,int currPage){
        Map param=new HashMap<String,Object>();
        HashMap<String,Object> retMap=new HashMap<>();
        if(StringUtils.isNotBlank(caseId)&&StringUtils.isNumeric(caseId)){
            param.put("caseId",Integer.parseInt(caseId));
        }
        if(StringUtils.isNotBlank(caseLibId)&&StringUtils.isNumeric(caseLibId)){
            param.put("caseLibId",Integer.parseInt(caseLibId));
        }
        if(StringUtils.isNotBlank(caseName)){
            param.put("caseName",caseName);
        }
        if(StringUtils.isNotBlank(caseParaType)){
            param.put("caseParaType",caseParaType);
        }
        if(currPage<=0) {
            currPage = 1;
        }
        param.put("currPage",currPage);
        retMap.put("list",testCaseService.queryTestCaseByPage(param));
        retMap.put("total",testCaseService.queryAmount(param));
        return retMap;
    }

    /**
     * 删除 ，根据主键
     * @param id
     * @return 0 不存在，1删除成功，-1违法主键约束
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    int deleteTestCaseById(int id){
        int ret = 0;
        try{
            ret = testCaseService.deleteTestCaseById(id);
        }catch (Exception e){
            ret=-1;
        }
        return ret;
    }

    /**
     * 更新
     * @param testCase
     * @return 0 不存在，1更新成功，-1违法主键约束
     */
    @ResponseBody
    @RequestMapping(value = "/update")
    int updateTestCase(TestCase testCase){
        int ret = 0;
        try{
            ret = testCaseService.updateTestCase(testCase);
        }catch (Exception e){
            ret=-1;
        }
        return ret;
    }

    /**
     * 插入
     * @param testCase
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insert")
    int insertTestCase(TestCase testCase){
        int ret = 0;
        try{
            ret = testCaseService.insertTestCase(testCase);
        }catch (Exception e){
            ret=-1;
        }
        return ret;
    }

    @ResponseBody
    @RequestMapping(value = "/categoryByParaType")
    List<ChartTypeDto> categoryByParaType(){
        return testCaseService.categoryByParaType();
    }

    @ResponseBody
    @RequestMapping(value = "/categoryByLibId")
    List<ChartTypeDto> categoryByLibId(){
        return testCaseService.categoryByLibId();
    }
}
