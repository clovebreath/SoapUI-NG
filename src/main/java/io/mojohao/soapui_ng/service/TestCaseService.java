package io.mojohao.soapui_ng.service;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestCase;

import java.util.List;
import java.util.Map;

public interface TestCaseService {
    /**
     * 获取所有测试用例
     * @return
     */
    List<TestCase> getAllTestCases();

    /**
     * 查询用例 根据ID
     * @param id
     * @return
     */
    TestCase queryTestCaseById(int id);

    /**
     * 根据条件查询
     * @param testCase
     * @return
     */
    List<TestCase> queryTestCaseByCondition(TestCase testCase);

    /**
     * 分页查询
     * @param param
     * @return
     */
    List<TestCase> queryTestCaseByPage(Map param);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteTestCaseById(int id);

    /**
     * 查询总条数，符合条件
     * @param param
     * @return
     */
    int queryAmount(Map param);

    /**
     * 更新
     * @param testCase
     * @return
     */
    int updateTestCase(TestCase testCase);

    /**
     * 插入
     * @param testCase
     * @return
     */
    int insertTestCase(TestCase testCase);

    /**
     * 分类 byparatype
     * @return
     */
    List<ChartTypeDto> categoryByParaType();

    /**
     * 分类 byLibID
     * @return
     */
    List<ChartTypeDto> categoryByLibId();
}
