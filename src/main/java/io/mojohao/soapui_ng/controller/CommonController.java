package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.ChartTypeDto;
import io.mojohao.soapui_ng.service.CommonService;
import io.mojohao.soapui_ng.util.WebServiceConsumer;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.XMLUnit;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/testRestConsumer")
    public Map execRest() throws Exception {
        HashMap<String,Object> para=new HashMap<>();
        para.put("userId",1);
        try {
            Map ret1 = serviceConsumer.consumeRestService("http://localhost:8080/servic", "", HttpMethod.GET, para);
//            Map ret2 = serviceConsumer.consumeRestService("http://localhost:8080/service/RESTExample/user_infos/{userId}", "{\"userId\":1,\"userType\":\"ddd\",\"userState\":\"ON\",\"userName\":\"das\",\"userAddress\":\"ff\",\"userElemCode\":\"aaaaaaaa\",\"userAreaCode\":\"d\"}", HttpMethod.PUT, para);
//            Map ret3 = serviceConsumer.consumeRestService("http://localhost:8080/service/RESTExample/user_infos/{userId}", "", HttpMethod.DELETE, para);
//            Map ret4 = serviceConsumer.consumeRestService("http://localhost:8080/service/RESTExample/user_infos", "{\"userId\":1,\"userType\":\"OPPP\",\"userState\":\"ON\",\"userName\":\"das\",\"userAddress\":\"ff\",\"userElemCode\":\"a\",\"userAreaCode\":\"d\"}", HttpMethod.POST, para);
        }catch(HttpClientErrorException e){
            e.getMessage();
        }finally {
            return para;
        }
    }
    @ResponseBody
    @RequestMapping(value = "/testSoapConsumer")
    public Map execSoap() throws Exception {
        String xml="<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
                " <soap:Body>\n" +
                "  <getData xmlns=\"http://api.soapui_ng.mojohao.io/\">\n" +
                "   <userId>2</userId>\n" +
                "    <elemId>32</elemId>\n" +
                "    <month>2018-05</month>\n" +
                "  </getData> \n" +
                " </soap:Body>\n" +
                "</soap:Envelope>";
        return serviceConsumer.consumeSoapService("http://localhost:8800/service/WSDLExample",xml);
    }

    public static void main(String[] args) throws Exception{
        WebServiceConsumer consumer=new WebServiceConsumer();
        String s8="   <book id=\"bk101\">\n" +
                "      <author>Gambardella, Matthew</author>\n" +
                "      <genre>Computer</genre>\n" +
                "      <title>XML Developer's Guide</title>\n" +
                "      <price>44.95</price>\n" +
                "      <publish_date>2000-10-01</publish_date>\n" +
                "      <description>An in-depth look at creating applications \n" +
                "      with XML.</description>\n" +
                "   </book>";
        String s7="   <book id=\"bk101\">\n" +
                "      <author>Gambardella, Matthew</author>\n" +
                "      <title>XML Developer's GuideEEEEEE</title>\n" +
                "      <genre>Computer</genre>\n" +
                "      <price>44.95</price>\n" +
                "      <publish_date>2000-10-01</publish_date>\n" +
                "      <description>An in-depth look at creating applications \n" +
                "      with XML.</description>\n" +
                "   </book>";
        String s6="   <book id=\"bk101\">\n" +
                "      <author>Gambardella, Matthew</author>\n" +
                "      <title>XML Developer's Guide</title>   \n" +
                "      <genre>Computer</genre>\n" +
                "      <price>44.95</price>\n" +
                "      <publish_date>2000-10-01</publish_date>\n" +
                "      <description>An in-depth look at creating applications \n" +
                "      with XML.</description>\n" +
                "   </book>";
        String s5="   <book id=\"bk101\">\n" +
                "      <author>Gambardella, Matthew    </author>\n" +
                "      <title>XML Developer's Guide</title>\n" +
                "      <genre>Computer</genre>\n" +
                "      <price>44.95</price>\n" +
                "      <publish_date>2000-10-01</publish_date>\n" +
                "      <description>An in-depth look at creating applications \n" +
                "      with XML.</description>\n" +
                "   </book>";
        String s3="   <book id=\"bk101\">\n" +
                "      <author>Gambardella, Matthew</author>\n" +
                "      <title>XML Developer's Guide</title>\n" +
                "      <genre>Computer</genre>\n" +
                "      <price>44.95</price>\n" +
                "      <publish_date>2000-10-01</publish_date>\n" +
                "      <description>An in-depth look at creating applications \n" +
                "      with XML.</description>\n" +
                "   </book>";
        String s4="   <book id=\"bk102\">\n" +
                "      <author>Gambardella, Matthew</author>\n" +
                "      <title>XML Developer's Guide</title>\n" +
                "      <genre>Computer</genre>\n" +
                "      <price>44.95</price>\n" +
                "      <publish_date>2000-10-01</publish_date>\n" +
                "      <description>An in-depth look at creating applications \n" +
                "      with XML.</description>\n" +
                "   </book>";
        String s9="   <book id=\"bk102\">\n" +
                "      <title>XML Developer's Guide</title>\n" +
                "      <genre>Computer</genre>\n" +
                "      <author>Matthew</author>\n" +
                "      <price>44</price>\n" +
                "      <publish_date>2000-10-01</publish_date>\n" +
                "      <description>An in-depth look at creating applications \n" +
                "      with XML.</description>\n" +
                "   </book>";
        String s0="   <book id=\"bk102\">\n" +
                "      <genre>Computer</genre>\n" +
                "      <price>44</price>\n" +
                "      <publish_date>2000-10-01</publish_date>\n" +
                "      <title>XML Developer's Guide</title>\n" +
                "      <author>Matthew</author>\n" +
                "      <description>An in-depth look at creating applications \n" +
                "      with XML.</description>\n" +
                "   </book>";
//        XMLUnit.setIgnoreWhitespace(true);

        XMLUnit.setIgnoreAttributeOrder(true);//忽略属性位置
        XMLUnit.setIgnoreComments(true);//忽略注解
        XMLUnit.setIgnoreDiffBetweenTextAndCDATA(true);
        XMLUnit.setIgnoreWhitespace(true);//忽略空白
        Diff diff49= XMLUnit.compareXML(s4,s9);
        Diff diff40= XMLUnit.compareXML(s4,s0);
        Diff diff90= XMLUnit.compareXML(s9,s0);
//        Diff diff36= XMLUnit.compareXML(s3,s6);
//        Diff diff37= XMLUnit.compareXML(s3,s7);
//        Diff diff38= XMLUnit.compareXML(s3,s8);
//        Diff diff56= XMLUnit.compareXML(s5,s6);
//        DetailedDiff detailedDiff = new DetailedDiff(diff);
//        System.out.println(ss);
//        System.out.print(consumer.formatXML(ss));

    }
}

