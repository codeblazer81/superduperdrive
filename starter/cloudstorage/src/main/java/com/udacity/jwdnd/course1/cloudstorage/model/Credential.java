package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential {
    
    private Integer credentialId;
    private String url;
    private String userkey;
    private Integer userId;
    private String username;
    private String password;


    public Credential() {
    }

    public Credential(Integer credentialId, String url, String userkey, Integer userId, String username, String password) {
        this.credentialId = credentialId;
        this.url = url;
        this.userkey = userkey;
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Credential(Integer credentialId, Integer userId) {
        this.credentialId = credentialId;
        this.userId = userId;
     
    }

    public Credential(Integer credentialId, String url, Integer userId, String username, String password) {
        this.credentialId = credentialId;
        this.url = url;
    
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    

    public Integer getCredentialId() {
        return this.credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserkey() {
        return this.userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
