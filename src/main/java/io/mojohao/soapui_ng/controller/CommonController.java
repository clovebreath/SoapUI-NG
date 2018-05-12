package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.service.CommonService;
import io.mojohao.soapui_ng.util.WebServiceConsumer;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

@Controller
@RequestMapping(value = "/common")
public class CommonController {




    @Autowired
    CommonService commonService;

    @Autowired
    WebServiceConsumer serviceConsumer;

    @ResponseBody
    @RequestMapping(value = "/countAll")
    public List<ChartTypeDto> countAll(){
        return commonService.countAll();
    }

    @ResponseBody
    @RequestMapping(value = "/execPlan/planId")
    public String execPlan() throws Exception {
        return serviceConsumer.consumeRestService("http://localhost:8080/service/RESTExample/userInfo?userId=1",1,"GET");
    }


    public static void main(String[] args) throws Exception{
        WebServiceConsumer consumer=new WebServiceConsumer();
//        String ss=consumer.getXML("12132323");
//        System.out.println(ss);
//        System.out.print(consumer.formatXML(ss));

    }
}

