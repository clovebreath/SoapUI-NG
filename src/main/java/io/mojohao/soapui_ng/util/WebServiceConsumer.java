package io.mojohao.soapui_ng.util;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Component
public class WebServiceConsumer {

    Logger logger=LoggerFactory.getLogger(WebServiceConsumer.class);

    @Autowired
    private RestTemplate restTemplate;

    static String SOAP12_PROPERTY="application/soap+xml; charset=utf-8";
    static String SOAP11_PROPERTY="text/xml; charset=utf-8";
    static String SOAP11_NAMESPACE="http://schemas.xmlsoap.org/soap/envelope/";
    static String SOAP12_NAMESPACE="http://www.w3.org/2003/05/soap-envelope";

    public String formatXML(String inputXML) throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new StringReader(inputXML));
        String requestXML = null;
        XMLWriter writer = null;
        if (document != null) {
            try {
                StringWriter stringWriter = new StringWriter();
                OutputFormat format = new OutputFormat(" ", true);
                writer = new XMLWriter(stringWriter, format);
                writer.write(document);
                writer.flush();
                requestXML = stringWriter.getBuffer().toString();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
        return requestXML;
    }

    public String getXML(String phoneNum){
        String soapXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                +"<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
                "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                +"<soap:Body>"
                +"<sayHello xmlns=\"http://api.soapui_ng.mojohao.io/\">"
                +"<arg1>"+phoneNum+"</arg1>"
                +"</sayHello>"
                +" </soap:Body>"
                +"</soap:Envelope>";
        return soapXML;
    }

    public HashMap<String,Object> consumeSoapService(String link, String soapXML) throws Exception{
        //1：创建服务地址
        URL url = new URL(link);
        //2：打开到服务地址的一个连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //3：设置连接参数
        connection.setConnectTimeout(5000);
        //3.1设置发送方式：POST必须大写
        connection.setRequestMethod("POST");
        //3.3根据namespace判断soap版本，设置数据格式：Content-type
        if(soapXML.contains(SOAP11_NAMESPACE)) {
            connection.setRequestProperty("content-type", SOAP11_PROPERTY);
        }else if(soapXML.contains(SOAP12_NAMESPACE)){
            connection.setRequestProperty("content-type", SOAP12_PROPERTY);
        }
        //3.4设置输入输出，新创建的connection默认是没有读写权限的，
        connection.setDoInput(true);
        connection.setDoOutput(true);

        //4：写入SOAP协议数据
        OutputStream os = connection.getOutputStream();
        os.write(soapXML.getBytes());

        //5：接收服务端的响应
        int responseCode = connection.getResponseCode();

        //返回Map
        HashMap<String,Object> retMap=new HashMap<>();
        retMap.put("responseCode",responseCode);

        //表示服务端响应成功
        if(200 == responseCode){
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            String temp = null;

            while(null != (temp = br.readLine())){
                sb.append(temp);
            }

            retMap.put("response",sb.toString());

            is.close();
            isr.close();
            br.close();
        }
        os.close();

        return retMap;
    }

    public String consumeRestService(String url, Object request, String accessMethod){

        String response="success";
        try{
            switch (accessMethod){
                case "GET":
                    response= restTemplate.getForObject(url,String.class);
                    break;
                case "POST":
                    response=restTemplate.postForObject(url,request,String.class);
                    break;
                case "PUT":
                    restTemplate.put(url,request);
                    break;
                case "DELETE":
                    restTemplate.delete(url);
                    break;
                default:
                    break;
            }
        }catch (RestClientException e){
            logger.error(e.toString());
        }
        return response;
    }


}
