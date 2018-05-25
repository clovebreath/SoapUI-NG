package io.mojohao.soapui_ng.service;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TestResultService {
    /**
     * 获取所有
     * @return
     */
    List<TestResult> getAllTestResults();

    /**
     * 根据id查询
     * @param resultId
     * @return
     */
    TestResult queryTestResultById(int resultId);

    /**
     * 根据条件查询
     * @param testResult
     * @return
     */
    List<TestResult> queryTestResultByCondition(TestResult testResult);

    /**
     * 分页查询
     * @param param
     * @return
     */
    List<TestResult> queryTestResultByPage(Map param);

    /**
     * 插入
     * @param testResult
     * @return
     */
    int insertTestResult(TestResult testResult);

    /**
     * 删除
     * @param resultId
     * @return
     */
    int deleteTestResult(int resultId);

    /**
     * 根据测试计划ID统计
     * @return
     */
    List<ChartTypeDto> categoryByTestPlanId();

    /**
     * 根据用例ID统计
     * @return
     */
    List<ChartTypeDto> categoryByCaseId();

    /**
     * 岑寂测试结果统计
     * @param testId
     * @return
     */
    List<ChartTypeDto> categoryByAssertion(String testId);
    /**
     * 统计符合条件的多少个
     * @return
     */
    int queryAmount(Map param);
}
