package io.mojohao.soapui_ng.service;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserInfoService {
    List<UserInfo> getAllUserInfos();

    UserInfo queryUserInfoById(@Param("userId") int userId);

    List<UserInfo> queryUserInfoByCondition(UserInfo userInfo);

    List<UserInfo> queryUserInfoByPage(Map param);

    int deleteUserInfoById(@Param("userId") int userId);

    int updateUserInfo(UserInfo userInfo);

    int insertUserInfo(UserInfo userInfo);

    List<ChartTypeDto> categoryByUserState();

    List<ChartTypeDto> categoryByUserType();

    List<ChartTypeDto> categoryByUserAreaCode();

    /**
     * 统计符合条件的多少个
     * @return
     */
    int queryAmount(Map param);
}
