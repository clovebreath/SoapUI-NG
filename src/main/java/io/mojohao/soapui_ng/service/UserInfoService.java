package io.mojohao.soapui_ng.service;

import io.mojohao.soapui_ng.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoService {
    List<UserInfo> getAllUserInfos();

    UserInfo queryUserInfoById(@Param("userId") int userId);

    List<UserInfo> queryUserInfoByCondition(UserInfo userInfo);

    int deleteUserInfoById(@Param("userId") int userId);

    int updateUserInfo(UserInfo userInfo);

    int insertUserInfo(UserInfo userInfo);
}
