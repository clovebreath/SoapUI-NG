package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.entity.UserInfo;
import io.mojohao.soapui_ng.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public HashMap<String,Object> queryUserInfoByCondition(String userName,String userState,String userElemCode,String userId,int currPage) {
        Map param=new HashMap<String,Object>();
        if(StringUtils.isNotBlank(userId)&&StringUtils.isNumeric(userId)){
            param.put("userId",Integer.parseInt(userId));
        }
        if(StringUtils.isNotBlank(userElemCode)){
            param.put("userElemCode",userElemCode);
        }
        if(StringUtils.isNotBlank(userName)){
            param.put("userName",userName);
        }
        if(StringUtils.isNotBlank(userState)){
            param.put("userState",userState);
        }
        if(currPage<=0) {
            currPage = 1;
        }
        param.put("currPage",currPage);
        HashMap<String,Object> retMap=new HashMap<>();
        retMap.put("list",userInfoService.queryUserInfoByPage(param));
        retMap.put("total",userInfoService.queryAmount(param));
        return retMap;
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
