package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestCaseLib;
import io.mojohao.soapui_ng.service.TestCaseLibService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/testCaseLib")
public class TestCaseLibController {
    @Autowired
    private TestCaseLibService testCaseLibService;

    /**
     * 查询所有
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/all")
    List<TestCaseLib> getAllTestCaseLibs(){
        return testCaseLibService.getAllTestCaseLibs();
    }

    /**
     * 查询一个 根据主键
     * @param id
     * @return
     */
    TestCaseLib queryTestCaseLibById(int id){
        return testCaseLibService.queryTestCaseLibById(id);
    }

    /**
     * 根据条件查询
     * @param libId
     * @param libName
     * @param applyApiId
     * @param currPage
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query")
    HashMap<String,Object> queryTestCaseLibByCondition(String libId,String libName,String applyApiId,int currPage){
        Map param=new HashMap<String,Object>();
        HashMap<String,Object> retMap=new HashMap<>();
        if(StringUtils.isNotBlank(libId)&&StringUtils.isNumeric(libId)){
            param.put("libId",Integer.parseInt(libId));
        }
        if(StringUtils.isNotBlank(libName)){
            param.put("libName",libName);
        }
        if(StringUtils.isNotBlank(applyApiId)&&StringUtils.isNumeric(applyApiId)){
            param.put("applyApiId",Integer.parseInt(applyApiId));
        }
        if(currPage<=0) {
            currPage = 1;
        }
        param.put("currPage",currPage);
        retMap.put("list",testCaseLibService.queryTestCaseLibByPage(param));
        retMap.put("total",testCaseLibService.queryAmount(param));
        return retMap;
    }

    /**
     * 删除，根据主键
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    int deleteTestCaseLibById(int id){
        int ret=0;
        try{
            ret=testCaseLibService.deleteTestCaseLibById(id);
        }catch (Exception e){
            ret=-1;
        }
        return ret;
    }

    /**
     * 更新
     * @param testCaseLib
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update")
    int updateTestCaseLib(TestCaseLib testCaseLib){
        int ret=0;
        try{
            ret=testCaseLibService.updateTestCaseLib(testCaseLib);
        }catch (Exception e){
            ret=-1;
        }
        return ret;
    }

    /**
     * 插入
     * @param testCaseLib
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insert")
    int insertTestCaseLib(TestCaseLib testCaseLib){
        int ret=0;
        try{
            ret=testCaseLibService.insertTestCaseLib(testCaseLib);
        }catch (Exception e){
            ret=-1;
        }
        return ret;
    }
    @ResponseBody
    @RequestMapping(value = "/categoryByApplyApiId")
    public List<ChartTypeDto> categoryByApplyApiId(){
        return testCaseLibService.categoryByApplyApiId();
    }
}
