package io.mojohao.soapui_ng.service.impl;

import io.mojohao.soapui_ng.entity.UserInfo;
import io.mojohao.soapui_ng.service.UserInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    public List<UserInfo> getAllUserInfos() {
        return null;
    }

    public UserInfo queryUserInfoById(int userId) {
        return null;
    }

    public List<UserInfo> queryUserInfoByCondition(UserInfo userInfo) {
        return null;
    }

    public int deleteUserInfoById(int userId) {
        return 0;
    }

    public int updateUserInfo(UserInfo userInfo) {
        return 0;
    }

    public int insertUserInfo(UserInfo userInfo) {
        return 0;
    }
}
