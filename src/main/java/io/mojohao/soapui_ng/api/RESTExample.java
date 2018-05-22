package io.mojohao.soapui_ng.api;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.mojohao.soapui_ng.entity.UserInfo;
import io.mojohao.soapui_ng.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/service/RESTExample")
public class RESTExample {
    @Autowired
    private UserInfoService userInfoService;

    Logger logger=LoggerFactory.getLogger(RESTExample.class);
    /*  *******************************************************
     *  GET /products : will return the list of all products
     *  POST /products : will add a product to the collection
     *  GET /products/4 : will retrieve product #4
     *  PATCH/PUT /products/4 : will update product #4
     *
     *  REST架构API设计例子
     *  用不同访问方法对同一资源的进行操作
     *  ******************************************************
     */


    /**
     * GET 获取单一实例信息
     * @param userId ID
     * @return
     */
    @RequestMapping(value = "/user_infos/{userId}",method = RequestMethod.GET)
    public UserInfo queryUserInfoById(@PathVariable int userId) {
        return userInfoService.queryUserInfoById(userId);
    }
    /**
     * DELETE 获取单一实例信息
     * @param userId ID
     * @return
     */
    @RequestMapping(value = "/user_infos/{userId}",method = RequestMethod.DELETE)
    public Map deleteUserInfoById(@PathVariable int userId) {
        int result=0;
        try {
            result=userInfoService.deleteUserInfoById(userId);
        }catch (Exception e){
            logger.error( e.getMessage() + ",in 删除一个实例");
        }
        Map retMap=new HashMap<String,Object>();
        if(0==result) {
            retMap.put("result","failed");
        }else {
            retMap.put("result","success");
            retMap.put("dataId",userId);
        }
        return retMap;
    }
    /**
     * PUT 更新一个实例
     * @param param 新用户参数
     * @return
     */
    @RequestMapping(value = "/user_infos/{userId}",method = RequestMethod.PUT)
    public Map putUserInfo(@RequestBody String param,@PathVariable int userId) {
        int result=0;
        try {
            UserInfo user = new Gson().fromJson(param, UserInfo.class);
            user.setUserId(userId);
            result = userInfoService.updateUserInfo(user);
        }catch (Exception e){
            logger.error( e.getMessage() + ",in 更新一个实例");
        }
        Map retMap=new HashMap<String,Object>();
        if(0==result) {
            retMap.put("result","failed");
        }else {
            retMap.put("result","success");
            retMap.put("dataId",userId);
        }
        return retMap;
    }

    /**
     * GET 获取所有实例信息
     * @return
     */
    @RequestMapping(value = "/user_infos",method = RequestMethod.GET)
    public List<UserInfo> queryUserInfos() {
        return userInfoService.getAllUserInfos();
    }

    /**
     * POST 新增一个实例
     * @param param JSON格式的参数
     * @return
     */
    @RequestMapping(value = "/user_infos",method = RequestMethod.POST)
    public Map postUserInfo(@RequestBody String param) {
        int result=0;
        UserInfo user=new UserInfo();
        try {
            user=new Gson().fromJson(param, UserInfo.class);
            result=userInfoService.insertUserInfo(user);
        }catch (Exception e){
            logger.error( e.getMessage() + ",in 新增一个实例");
        }
        Map retMap=new HashMap<String,Object>();
        if(0==result) {
            retMap.put("result","failed");
        }else {
            retMap.put("result","success");
            retMap.put("dataId",user.getUserId());
        }
        return retMap;
    }

}
