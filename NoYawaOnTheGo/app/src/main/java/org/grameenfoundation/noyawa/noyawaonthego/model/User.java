package org.grameenfoundation.noyawa.noyawaonthego.model;

/**
 * Created by mac on 1/25/16.
 */
public class User {

    String username;
    String userid;
    String loginDate;
    String loginTime;
    String status;


    public User()
    {

    }

    public String getUserId() {
        return userid;
    }
    public void setUserId(String userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


    public String getLoginDate() {
        return loginDate;
    }
    public void setLoginDate(String loginDate) {
        this.loginDate =loginDate;
    }


    public String getLoginTime() {
        return loginTime;
    }
    public void setLoginTime(String loginTime) {
        this.loginTime =loginTime;
    }


    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}