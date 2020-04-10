package com.example.tonyn.youqiu;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by tonyn on 2017/5/4.
 */

public class Organization extends BmobObject {
    private String orgName;
    private String startTime;
    private String orgPerson;
    private ArrayList<String> contentList;

    public ArrayList<String> getContentList() {
        return contentList;
    }

    public void setContentList(ArrayList<String> contentList) {
        this.contentList = contentList;
    }

    private long orgNumber;

    public String getOrgPerson() {
        return orgPerson;
    }

    public void setOrgPerson(String orgPerson) {
        this.orgPerson = orgPerson;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public long getOrgNumber() {
        return orgNumber;
    }

    public void setOrgNumber(long orgNumber) {
        this.orgNumber = orgNumber;
    }

}
