package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.UserInfo;
import io.mojohao.soapui_ng.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

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
