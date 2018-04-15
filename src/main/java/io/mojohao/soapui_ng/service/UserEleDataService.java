package io.mojohao.soapui_ng.service;

import io.mojohao.soapui_ng.entity.UserEleData;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface UserEleDataService {
    List<UserEleData> getAllUserEleDatas();

    UserEleData queryUserEleDataById(@Param("userId") int userId, @Param("collectTime") Timestamp collectTime);

    List<UserEleData> queryUserEleDataByCondition(UserEleData userEleData);

    int deleteUserEleDataById(@Param("userId") int userId, @Param("collectTime") Timestamp collectTime);

    int updateUserEleData(UserEleData userEleData);

    int insertUserEleData(UserEleData userEleData);
}
