package io.mojohao.soapui_ng.entity;

import java.sql.Timestamp;

public class UserEleData {
    private int userId;
    private Timestamp collectTime;
    private String elemId;
    private String elemData;

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
