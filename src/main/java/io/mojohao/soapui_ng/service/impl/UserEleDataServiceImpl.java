package io.mojohao.soapui_ng.service.impl;

import io.mojohao.soapui_ng.entity.UserEleData;
import io.mojohao.soapui_ng.service.UserEleDataService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserEleDataServiceImpl implements UserEleDataService {
    public List<UserEleData> getAllUserEleDatas() {
        return null;
    }

    public UserEleData queryUserEleDataById(int userId, Timestamp collectTime) {
        return null;
    }

    public List<UserEleData> queryUserEleDataByCondition(UserEleData userEleData) {
        return null;
    }

    public int deleteUserEleDataById(int userId, Timestamp collectTime) {
        return 0;
    }

    public int updateUserEleData(UserEleData userEleData) {
        return 0;
    }

    public int insertUserEleData(UserEleData userEleData) {
        return 0;
    }
}
