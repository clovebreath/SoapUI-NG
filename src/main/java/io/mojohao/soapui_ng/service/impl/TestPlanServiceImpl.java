package io.mojohao.soapui_ng.service.impl;

import io.mojohao.soapui_ng.dao.TestPlanDao;
import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.TestPlan;
import io.mojohao.soapui_ng.service.TestPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestPlanServiceImpl implements TestPlanService {
    @Autowired
    private TestPlanDao testPlanDao;

    @Override
    public List<TestPlan> getAllTestPlans() {
        return testPlanDao.getAllTestPlans();
    }

    @Override
    public TestPlan queryTestPlanById(int id) {
        return testPlanDao.queryTestPlanById(id);
    }

    @Override
    public List<TestPlan> queryTestPlanByCondition(TestPlan testPlan) {
        return testPlanDao.queryTestPlanByCondition(testPlan);
    }

    @Override
    public List<TestPlan> queryTestPlanByPage(Map map) {
        return testPlanDao.queryTestPlanByPage(map);
    }

    @Override
    public int deleteTestPlanById(int planId) {
        return testPlanDao.deleteTestPlanById(planId);
    }

    @Override
    public int updateTestPlan(TestPlan plan) {
        return testPlanDao.updateTestPlan(plan);
    }

    @Override
    public int insertTestPlan(TestPlan plan) {
        return testPlanDao.insertTestPlan(plan);
    }

    @Override
    public List<ChartTypeDto> categoryByLibId() {
        return testPlanDao.categoryByLibId();
    }

    @Override
    public List<ChartTypeDto> categoryByApiId() {
        return testPlanDao.categoryByApiId();
    }

    @Override
    public int countAll() {
        return testPlanDao.countAll();
    }

    @Override
    public int queryAmount(Map param) {
        return testPlanDao.queryAmount(param);
    }
}
