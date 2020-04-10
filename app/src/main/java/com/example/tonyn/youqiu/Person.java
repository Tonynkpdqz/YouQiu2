package com.example.tonyn.youqiu;

import cn.bmob.v3.BmobObject;

/**
 * Created by tonyn on 2017/3/12.
 */

public class Person extends BmobObject {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
