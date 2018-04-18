package io.mojohao.soapui_ng.dao;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.UserEleData;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface UserEleDataDao {
    List<UserEleData> getAllUserEleDatas();

    UserEleData queryUserEleDataById(@Param("dataId") int dataId);

    List<UserEleData> queryUserEleDataByCondition(UserEleData userEleData);

    int deleteUserEleDataById(@Param("dataId") int dataId);

    int updateUserEleData(UserEleData userEleData);

    int insertUserEleData(UserEleData userEleData);

    List<ChartTypeDto> categoryByUserId();

}
