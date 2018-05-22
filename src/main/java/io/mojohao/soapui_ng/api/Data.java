package io.mojohao.soapui_ng.api;

/**
 * 作为WSDL接口返回信息的载体
 */
public class Data{
    /**
     * 返回信息
     */
    String message;
    /**
     * 返回数据
     */
    String data;

    public Data() {
    }

    public Data(String message, String data){
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}