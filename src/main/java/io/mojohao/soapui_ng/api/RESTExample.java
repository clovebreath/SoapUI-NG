package io.mojohao.soapui_ng.api;


import io.mojohao.soapui_ng.entity.UserInfo;
import io.mojohao.soapui_ng.service.UserInfoService;
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
     * PUT 更新一个实例
     * @param user 新用户
     * @return
     */
    @RequestMapping(value = "/user_infos/{userId}",method = RequestMethod.PUT)
    public Map putUserInfo(@PathVariable int userId,UserInfo user) {
        user.setUserId(userId);
        int result=userInfoService.updateUserInfo(user);
        Map retMap=new HashMap<String,Object>();
        if(0==result) {
            retMap.put("result","failed");
        }else {
            retMap.put("result","success");
            retMap.put("newId",result);
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
     * @param user 新用户
     * @return
     */
    @RequestMapping(value = "/user_infos",method = RequestMethod.POST)
    public Map postUserInfo(UserInfo user) {
        int result=userInfoService.insertUserInfo(user);
        Map retMap=new HashMap<String,Object>();
        if(0==result) {
            retMap.put("result","failed");
        }else {
            retMap.put("result","success");
            retMap.put("newId",result);
        }
        return retMap;
    }

}
