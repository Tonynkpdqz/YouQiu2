package com.example.tonyn.youqiu;

import cn.bmob.v3.BmobObject;

/**
 * Created by tonyn on 2017/3/13.
 */

public class Player extends BmobObject {
    private String myname;
    private String myschool;
    private String phonenumber;

    public String getMyname() {
        return myname;
    }

    public void setMyname(String myname) {
        this.myname = myname;
    }

    public String getMyschool() {
        return myschool;
    }

    public void setMyschool(String myschool) {
        this.myschool = myschool;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
