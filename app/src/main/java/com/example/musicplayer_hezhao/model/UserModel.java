package com.example.musicplayer_hezhao.model;

/**
 * Created by 11120555 on 2020/7/13 15:41
 */
public class UserModel {
    //用户名,昵称
    private String Username;
    //性别
    private boolean sex;
    //用户密码
    private String Password;
    //用户电话，用于找回密码
    private String Phonenumber;
    //用户头像
    private String user_pic;
    //用户签名
    private String user_word;
    //关注的人
    private String collect_name;
    //粉丝
    private String collected_name;

    public UserModel(String username, String password) {
        Username = username;
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }

    public String getUser_pic() {
        return user_pic;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }

    public String getUser_word() {
        return user_word;
    }

    public void setUser_word(String user_word) {
        this.user_word = user_word;
    }

    public String getCollect_name() {
        return collect_name;
    }

    public void setCollect_name(String collect_name) {
        this.collect_name = collect_name;
    }

    public String getCollected_name() {
        return collected_name;
    }

    public void setCollected_name(String collected_name) {
        this.collected_name = collected_name;
    }
}
