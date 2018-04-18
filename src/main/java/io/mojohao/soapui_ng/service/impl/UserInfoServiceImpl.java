package io.mojohao.soapui_ng.service.impl;

import io.mojohao.soapui_ng.dao.UserInfoDao;
import io.mojohao.soapui_ng.entity.UserInfo;
import io.mojohao.soapui_ng.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoDao userInfoDao;

    public List<UserInfo> getAllUserInfos() {
        return userInfoDao.getAllUserInfos();
    }

    public UserInfo queryUserInfoById(int userId) {
        return userInfoDao.queryUserInfoById(userId);
    }

    public List<UserInfo> queryUserInfoByCondition(UserInfo userInfo) {
        return userInfoDao.queryUserInfoByCondition(userInfo);
    }

    public int deleteUserInfoById(int userId) {
        return userInfoDao.deleteUserInfoById(userId);
    }

    public int updateUserInfo(UserInfo userInfo) {
        return userInfoDao.updateUserInfo(userInfo);
    }

    public int insertUserInfo(UserInfo userInfo) {
        return userInfoDao.insertUserInfo(userInfo);
    }
}
