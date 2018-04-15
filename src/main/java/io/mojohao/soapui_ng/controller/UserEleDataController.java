package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.UserEleData;
import io.mojohao.soapui_ng.service.UserEleDataService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping(value = "/userEleData")
public class UserEleDataController {
    @Autowired
    private UserEleDataService userEleDataService;

    List<UserEleData> getAllUserEleDatas(){
        return null;
    }

    UserEleData queryUserEleDataById(@Param("userId") int userId, @Param("collectTime") Timestamp collectTime){
        return null;
    }

    List<UserEleData> queryUserEleDataByCondition(UserEleData userEleData){
        return null;
    }

    int deleteUserEleDataById(@Param("userId") int userId, @Param("collectTime") Timestamp collectTime){
        return userId;
    }

    int updateUserEleData(UserEleData userEleData){
        return 0;
    }

    int insertUserEleData(UserEleData userEleData){
        return 0;
    }
}
