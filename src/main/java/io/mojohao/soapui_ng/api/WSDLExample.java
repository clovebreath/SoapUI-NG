package io.mojohao.soapui_ng.api;

import io.mojohao.soapui_ng.dao.UserEleDataDao;
import io.mojohao.soapui_ng.entity.UserEleData;
import io.mojohao.soapui_ng.entity.UserInfo;
import io.mojohao.soapui_ng.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.text.ParseException;
import java.util.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Component
@WebService(serviceName="WSDLExample")
public class WSDLExample {

    final String namespace="http://api.soapui_ng.mojohao.io/";

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserEleDataDao userEleDataDao;

    @WebMethod
    public String sayHello(@WebParam(name = "str",targetNamespace=namespace)String str){
        System.out.println("get Message...");
        String result = "Hello World, "+str;
        return result;
    }

    @WebMethod
    public List<UserEleData> getDataS(@WebParam(name = "userId",targetNamespace=namespace) int userId,
                           @WebParam(name = "elemId",targetNamespace=namespace) String elemId){
        System.out.println("get Message...");
        UserEleData data=new UserEleData();
        data.setUserId(userId);
        data.setElemId(elemId);
        List<UserEleData> listOld=userEleDataDao.queryUserEleDataByCondition(data);
        return listOld;
    }

    @WebMethod
    public String getData(@WebParam(name = "userId",targetNamespace=namespace) int userId,
                            @WebParam(name = "elemId",targetNamespace=namespace) String elemId,
                            @WebParam(name = "month",targetNamespace=namespace) String month)  {
        HashMap<String,String> ret= new HashMap<>();
        System.out.println("get Message...");
        UserEleData data=new UserEleData();
        data.setUserId(userId);
        data.setElemId(elemId);
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(dateFormat.parse(month).getTime()));
            data.setCollectTime(new java.sql.Timestamp(calendar.getTime().getTime()));
            //需要的月份
            List<UserEleData> listOld=userEleDataDao.queryUserEleDataByCondition(data);
            //默认月初抄表
            calendar.add(Calendar.MONTH, 1);
            data.setCollectTime(new java.sql.Timestamp(calendar.getTime().getTime()));
            //下一个月份
            List<UserEleData> listNew=userEleDataDao.queryUserEleDataByCondition(data);
            if(1==listOld.size()&&1==listNew.size()){
                ret.put("message","successed");
                ret.put("data",String.valueOf(listNew.get(0).getElemData()-listOld.get(0).getElemData()));
            }else{
                ret.put("message","invalid data");
            }
        }catch (ParseException e){
            ret.put("message","failed");
        }
        return  ret.toString();
    }


    public static void main(String[] args) {
//        System.out.println("server is running");
//        String address="http://localhost:8800/service/WSDLExample";
//        Object implementor =new WSDLExample();
//        Endpoint.publish(address, implementor);

//        Timestamp stamp = new Timestamp(System.currentTimeMillis());
//        Date date = new Date(stamp.getTime());
//        System.out.println(date);
//        Calendar rightNow = Calendar.getInstance();
//        rightNow.setTime(date);
//        rightNow.add(Calendar.MONTH, 1);
//
//        Date dt1 =  rightNow.getTime();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
//        String string  = dateFormat.format(date);
//        System.out.println(string);
//
//        String reStr = dateFormat.format(dt1);
//        System.out.println(reStr);

    }
}
