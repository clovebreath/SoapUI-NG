package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.UserInfo;
import io.mojohao.soapui_ng.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @ResponseBody
    @RequestMapping(value = "/all")
    public List<UserInfo> getAllUserInfos() {
        return userInfoService.getAllUserInfos();
    }

    public UserInfo queryUserInfoById(int userId) {
        return userInfoService.queryUserInfoById(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/query")
    public List<UserInfo> queryUserInfoByCondition(String userName,String userState,String userElemCode,String userId) {
        UserInfo userInfo=new UserInfo();
        if(StringUtils.isNotBlank(userId)&&StringUtils.isNumeric(userId)){
            userInfo.setUserId(Integer.parseInt(userId));
        }
        if(StringUtils.isNotBlank(userElemCode)){
            userInfo.setUserElemCode(userElemCode);
        }
        if(StringUtils.isNotBlank(userName)){
            userInfo.setUserName(userName);
        }
        if(StringUtils.isNotBlank(userState)){
            userInfo.setUserState(userState);
        }
        return userInfoService.queryUserInfoByCondition(userInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public int deleteUserInfoById(int userId) {
        return userInfoService.deleteUserInfoById(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public int updateUserInfo(UserInfo userInfo) {
        return userInfoService.updateUserInfo(userInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/insert")
    public int insertUserInfo(UserInfo userInfo) {
        return userInfoService.insertUserInfo(userInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/categoryByUserState")
    List<ChartTypeDto> categoryByUserState(){
        return userInfoService.categoryByUserState();
    }
    @ResponseBody
    @RequestMapping(value = "/categoryByUserType")
    List<ChartTypeDto> categoryByUserType(){
        return userInfoService.categoryByUserType();
    }
    @ResponseBody
    @RequestMapping(value = "/categoryByUserAreaCode")
    List<ChartTypeDto> categoryByUserAreaCode(){
        return userInfoService.categoryByUserAreaCode();
    }
}
