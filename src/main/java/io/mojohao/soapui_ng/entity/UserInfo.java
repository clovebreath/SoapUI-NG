package io.mojohao.soapui_ng.entity;

public class UserInfo {
    private int userId;
    private String userType;
    private String userState;
    private String userName;
    private String userAddress;
    private String userElemCode;
    private String userAreaCode;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserElemCode() {
        return userElemCode;
    }

    public void setUserElemCode(String userElemCode) {
        this.userElemCode = userElemCode;
    }

    public String getUserAreaCode() {
        return userAreaCode;
    }

    public void setUserAreaCode(String userAreaCode) {
        this.userAreaCode = userAreaCode;
    }
}
