package io.mojohao.soapui_ng.service;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestCaseLib;

import java.util.List;
import java.util.Map;

public interface TestCaseLibService {
    /**
     * 查询全部
     * @return
     */
    List<TestCaseLib> getAllTestCaseLibs();

    /**
     * 查询一个
     * @param id
     * @return
     */
    TestCaseLib queryTestCaseLibById(int id);

    /**
     * 查询根据条件
     * @param testCaseLib
     * @return
     */
    List<TestCaseLib> queryTestCaseLibByCondition(TestCaseLib testCaseLib);

    /**
     * 分页查询
     * @param param
     * @return
     */
    List<TestCaseLib> queryTestCaseLibByPage(Map param);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteTestCaseLibById(int id);

    /**
     * 查询总数 符合条件
     * @param param
     * @return
     */
    int queryAmount(Map param);

    /**
     * 更新
     * @param testCaseLib
     * @return
     */
    int updateTestCaseLib(TestCaseLib testCaseLib);

    /**
     * 插入
     * @param testCaseLib
     * @return
     */
    int insertTestCaseLib(TestCaseLib testCaseLib);
    List<ChartTypeDto> categoryByApplyApiId();
}
