package io.mojohao.soapui_ng.service;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.UserEleData;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface UserEleDataService {
    List<UserEleData> getAllUserEleDatas();

    UserEleData queryUserEleDataById(int dataId);

    List<UserEleData> queryUserEleDataByCondition(UserEleData userEleData);

    List<UserEleData> queryUserEleDataByPage(Map param);

    int deleteUserEleDataById(int dataId);

    int updateUserEleData(UserEleData userEleData);

    int insertUserEleData(UserEleData userEleData);

    List<ChartTypeDto> categoryByUserId();
    /**
     * 统计符合条件的多少个
     * @return
     */
    int queryAmount(Map param);
}
