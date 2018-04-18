package io.mojohao.soapui_ng.service.impl;

import io.mojohao.soapui_ng.dao.UserEleDataDao;
import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.UserEleData;
import io.mojohao.soapui_ng.service.UserEleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserEleDataServiceImpl implements UserEleDataService {

    @Autowired
    UserEleDataDao userEleDataDao;

    public List<UserEleData> getAllUserEleDatas() {
        return userEleDataDao.getAllUserEleDatas();
    }

    public UserEleData queryUserEleDataById(int dataId) {
        return userEleDataDao.queryUserEleDataById(dataId);
    }

    public List<UserEleData> queryUserEleDataByCondition(UserEleData userEleData) {
        return userEleDataDao.queryUserEleDataByCondition(userEleData);
    }

    public int deleteUserEleDataById(int dataId) {
        return userEleDataDao.deleteUserEleDataById(dataId);
    }

    public int updateUserEleData(UserEleData userEleData) {
        return userEleDataDao.updateUserEleData(userEleData);
    }

    public int insertUserEleData(UserEleData userEleData) {
        return userEleDataDao.insertUserEleData(userEleData);
    }

    public List<ChartTypeDto> categoryByUserId() {
        return userEleDataDao.categoryByUserId();
    }
}
