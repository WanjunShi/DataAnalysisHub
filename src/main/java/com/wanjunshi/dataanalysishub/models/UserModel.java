package com.wanjunshi.dataanalysishub.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

public class UserModel implements Serializable {
    private String username;
    private String fname;
    private String lname;
    private String password;

    private boolean vip;

    private HashMap<Integer, PostModel> posts;

    public HashMap<Integer, PostModel> getPosts() {
        return posts;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public UserModel(String username, String fname, String lname, String password) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.vip = false;
        posts = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
