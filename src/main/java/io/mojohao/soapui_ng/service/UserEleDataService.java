package io.mojohao.soapui_ng.service;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.UserEleData;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface UserEleDataService {
    List<UserEleData> getAllUserEleDatas();

    UserEleData queryUserEleDataById(int dataId);

    List<UserEleData> queryUserEleDataByCondition(UserEleData userEleData);

    int deleteUserEleDataById(int dataId);

    int updateUserEleData(UserEleData userEleData);

    int insertUserEleData(UserEleData userEleData);

    List<ChartTypeDto> categoryByUserId();

}
