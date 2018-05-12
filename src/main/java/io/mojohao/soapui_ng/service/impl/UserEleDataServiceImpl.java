package io.mojohao.soapui_ng.service.impl;

import io.mojohao.soapui_ng.dao.UserEleDataDao;
import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.UserEleData;
import io.mojohao.soapui_ng.service.UserEleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class UserEleDataServiceImpl implements UserEleDataService {

    @Autowired
    UserEleDataDao userEleDataDao;

    @Override
    public List<UserEleData> getAllUserEleDatas() {
        return userEleDataDao.getAllUserEleDatas();
    }

    @Override
    public UserEleData queryUserEleDataById(int dataId) {
        return userEleDataDao.queryUserEleDataById(dataId);
    }

    @Override
    public List<UserEleData> queryUserEleDataByCondition(UserEleData userEleData) {
        return userEleDataDao.queryUserEleDataByCondition(userEleData);
    }
    @Override
    public List<UserEleData> queryUserEleDataByPage(Map param) {
        return userEleDataDao.queryUserEleDataByPage(param);
    }
    @Override
    public int deleteUserEleDataById(int dataId) {
        return userEleDataDao.deleteUserEleDataById(dataId);
    }

    @Override
    public int updateUserEleData(UserEleData userEleData) {
        return userEleDataDao.updateUserEleData(userEleData);
    }

    @Override
    public int insertUserEleData(UserEleData userEleData) {
        return userEleDataDao.insertUserEleData(userEleData);
    }

    @Override
    public List<ChartTypeDto> categoryByUserId() {
        return userEleDataDao.categoryByUserId();
    }

    @Override
    public int queryAmount(Map param) {
        return userEleDataDao.queryAmount(param);
    }

}
