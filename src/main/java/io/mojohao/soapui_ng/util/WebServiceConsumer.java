package io.mojohao.soapui_ng.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebServiceConsumer {

    Logger logger=LoggerFactory.getLogger(WebServiceConsumer.class);

    @Autowired
    private RestTemplate restTemplate;

    static String SOAP12_PROPERTY="application/soap+xml; charset=utf-8";
    static String SOAP11_PROPERTY="text/xml; charset=utf-8";
    static String SOAP11_NAMESPACE="http://schemas.xmlsoap.org/soap/envelope/";
    static String SOAP12_NAMESPACE="http://www.w3.org/2003/05/soap-envelope";

    /**
     * 格式化xml
     * @param inputXML
     * @return
     * @throws Exception
     */
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

    /**
     * 调用一个Soap接口，并获取返回值
     * @param link 接口地址
     * @param soapXML xml格式报文
     * @return
     * @throws Exception
     */
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

    /**
     * 调用REST接口
     * @param url 地址
     * @param requestBody 报文
     * @param method 方法
     * @param pathParam 参数
     * @return
     * @throws HttpClientErrorException
     */
    public HashMap<String,Object> consumeRestService(String url, String requestBody, HttpMethod method, Map pathParam)throws HttpClientErrorException {
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        MimeType mimeType = MimeTypeUtils.parseMimeType("application/json");
        MediaType mediaType = new MediaType(mimeType.getType(), mimeType.getSubtype(), Charset.forName("UTF-8"));
        // 请求体
        headers.setContentType(mediaType);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, method, entity, String.class,pathParam);
        // 获取返回状态码
        int responseCode = response.getStatusCode().value();
        //返回Map
        HashMap<String,Object> retMap=new HashMap<>();
        retMap.put("responseCode",responseCode);
        //如果状态码为200成功
        if(200==responseCode){
            retMap.put("response",response.getBody());
        }
        return retMap;
    }

    /**
     * 比较两个xml是否相同（只看内容，忽略结构）
     * @param xmlString1
     * @param xmlString2
     * @return true or false
     */
    public boolean compareXml(String xmlString1,String xmlString2){
        boolean assertion=false;
        //忽略属性位置
        XMLUnit.setIgnoreAttributeOrder(true);
        //忽略注解
        XMLUnit.setIgnoreComments(true);
        //忽略CDATA节点
        XMLUnit.setIgnoreDiffBetweenTextAndCDATA(true);
        //忽略空白
        XMLUnit.setIgnoreWhitespace(true);
        try {
            Diff diff= XMLUnit.compareXML(xmlString1,xmlString2);
            assertion=diff.similar();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error while compareXml,xml1:"+xmlString1+",xml2:"+xmlString2);
            if(xmlString1.equals(xmlString2)){
                assertion=true;
            }
        }
        return assertion;
    }

    /**
     * 比较两个JSON是否相同（只看内容，忽略结构）
     * @param json1
     * @param json2
     * @return
     */
    public boolean compareJson(String json1,String json2){
        boolean assertion=false;
        try{
            //使用gson将json转化为JsonObject然后对两个Object进行比较
            JsonParser parser = new JsonParser();
            JsonObject obj = (JsonObject) parser.parse(json1);
            JsonParser parser1 = new JsonParser();
            JsonObject obj1 = (JsonObject) parser1.parse(json2);
            assertion=obj.equals(obj1);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Error while compareJson,JSON1:"+json1+",JSON2:"+json2);
            if(json1.equals(json2)){
                assertion=true;
            }
        }
        return assertion;
    }
}
