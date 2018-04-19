package io.mojohao.soapui_ng.service.impl;

import io.mojohao.soapui_ng.dao.*;
import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    ApiDao apiDao;
    @Autowired
    TestCaseDao testCaseDao;
    @Autowired
    TestCaseLibDao testCaseLibDao;
    @Autowired
    TestResultDao testResultDao;
    @Autowired
    UserEleDataDao userEleDataDao;
    @Autowired
    UserInfoDao userInfoDao;

    public List<ChartTypeDto> countAll() {
        List<ChartTypeDto> account=new ArrayList<ChartTypeDto>();
        account.add(new ChartTypeDto("服务接口",apiDao.countAll()));
        account.add(new ChartTypeDto("测试用例",testCaseDao.countAll()));
        account.add(new ChartTypeDto("测试用例库",testCaseLibDao.countAll()));
        account.add(new ChartTypeDto("测试结果",testResultDao.countAll()));
        account.add(new ChartTypeDto("用户档案",userInfoDao.countAll()));
        account.add(new ChartTypeDto("采集电表数据",userEleDataDao.countAll()));
        return account;
    }
}
