package io.mojohao.soapui_ng.service;

import io.mojohao.soapui_ng.entity.Api;
import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestCase;
import io.mojohao.soapui_ng.entity.TestPlan;

import java.util.List;
import java.util.Map;

public interface TestPlanService {
    /**
     * 获取所有TestPlan
     * @return TestPlan list
     */
    List<TestPlan> getAllTestPlans();

    /**
     * 根据id查询TestPlan
     * @param id
     * @return TestPlan
     */
    TestPlan queryTestPlanById(int id);

    /**
     * 根据条件查询TestPlan
     * @param testPlan 条件容器
     * @return
     */
    List<TestPlan> queryTestPlanByCondition(TestPlan testPlan);

    /**
     * 根据条件查询TestPlan 分页
     * @param map 条件容器
     * @return
     */
    List<TestPlan> queryTestPlanByPage(Map map);

    /**
     * 删除plan
     * @param planId
     * @return
     */
    int deleteTestPlanById(int planId);

    /**
     * 更新plan
     * @param plan
     * @return
     */
    int updateTestPlan(TestPlan plan);

    /**
     * 更新plan
     * @param plan
     * @return
     */
    int updateTestPlanStatus(TestPlan plan);

    /**
     * 插入新的plan
     * @param plan
     * @return
     */
    int insertTestPlan(TestPlan plan);

    /**
     * 按照LibId统计数据
     * @return
     */
    List<ChartTypeDto> categoryByLibId();

    /**
     * 按照ApiId统计数据
     * @return
     */
    List<ChartTypeDto> categoryByApiId();

    /**
     * 按照status统计数据
     * @return
     */
    List<ChartTypeDto> categoryByStatus();

    /**
     * 统计一共多少个
     * @return
     */
    int countAll();

    /**
     * 统计符合条件的多少个
     * @return
     */
    int queryAmount(Map param);

    /**
     * 执行测试计划
     * @return
     */
    int excutePlan(Api api,List<TestCase> caseList,TestPlan plan);
}
