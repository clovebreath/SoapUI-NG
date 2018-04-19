package io.mojohao.soapui_ng.dao;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoDao {
    List<UserInfo> getAllUserInfos();

    UserInfo queryUserInfoById(@Param("userId") int userId);

    List<UserInfo> queryUserInfoByCondition(UserInfo userInfo);

    int deleteUserInfoById(@Param("userId") int userId);

    int updateUserInfo(UserInfo userInfo);

    int insertUserInfo(UserInfo userInfo);

    List<ChartTypeDto> categoryByUserState();

    List<ChartTypeDto> categoryByUserType();

    List<ChartTypeDto> categoryByUserAreaCode();

    int countAll();
}
