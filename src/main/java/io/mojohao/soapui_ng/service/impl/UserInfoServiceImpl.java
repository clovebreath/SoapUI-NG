package io.mojohao.soapui_ng.service.impl;

import io.mojohao.soapui_ng.dao.UserInfoDao;
import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.UserInfo;
import io.mojohao.soapui_ng.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoDao userInfoDao;

    @Override
    public List<UserInfo> getAllUserInfos() {
        return userInfoDao.getAllUserInfos();
    }

    @Override
    public UserInfo queryUserInfoById(int userId) {
        return userInfoDao.queryUserInfoById(userId);
    }

    @Override
    public List<UserInfo> queryUserInfoByCondition(UserInfo userInfo) {
        return userInfoDao.queryUserInfoByCondition(userInfo);
    }
    @Override
    public List<UserInfo> queryUserInfoByPage(Map param) {
        return userInfoDao.queryUserInfoByPage(param);
    }
    @Override
    public int deleteUserInfoById(int userId) {
        return userInfoDao.deleteUserInfoById(userId);
    }

    @Override
    public int updateUserInfo(UserInfo userInfo) {
        return userInfoDao.updateUserInfo(userInfo);
    }

    @Override
    public int insertUserInfo(UserInfo userInfo) {
        return userInfoDao.insertUserInfo(userInfo);
    }

    @Override
    public List<ChartTypeDto> categoryByUserState() {
        return userInfoDao.categoryByUserState();
    }

    @Override
    public List<ChartTypeDto> categoryByUserType() {
        return userInfoDao.categoryByUserType();
    }

    @Override
    public List<ChartTypeDto> categoryByUserAreaCode() {
        return userInfoDao.categoryByUserAreaCode();
    }

    @Override
    public int queryAmount(Map param) {
        return userInfoDao.queryAmount(param);
    }

}
