package io.mojohao.soapui_ng.dao;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TestResultDao {
    /**
     * 获取所有结果
     * @return
     */
    List<TestResult> getAllTestResults();

    /**
     * 根据id查询
     * @param resultId
     * @return
     */
    TestResult queryTestResultById(@Param("resultId") int resultId);

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
     * 新增
     * @param testResult
     * @return
     */
    int insertTestResult(TestResult testResult);

    /**
     * 删除
     * @param resultId
     * @return
     */
    int deleteTestResult(@Param("resultId") int resultId);

    /**
     * 根据测试计划ID统计
     * @return
     */
    List<ChartTypeDto> categoryByTestPlanId();

    /**
     * 根据测试用例统计
     * @return
     */
    List<ChartTypeDto> categoryByCaseId();

    /**
     * 根据测试结果统计
     * @return
     */
    List<ChartTypeDto> categoryByAssertion(@Param("testId") String testId);

    /**
     * 统计总数
     * @return
     */
    int countAll();
    /**
     * 统计符合条件的多少个
     * @return
     */
    int queryAmount(Map param);
}
