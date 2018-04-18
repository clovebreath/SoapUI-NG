package io.mojohao.soapui_ng.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class UserEleData {

    private int dataId;

    private int userId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp collectTime;

    private String elemId;

    private String elemData;


    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Timestamp collectTime) {
        this.collectTime = collectTime;
    }

    public String getElemId() {
        return elemId;
    }

    public void setElemId(String elemId) {
        this.elemId = elemId;
    }

    public String getElemData() {
        return elemData;
    }

    public void setElemData(String elemData) {
        this.elemData = elemData;
    }
}
