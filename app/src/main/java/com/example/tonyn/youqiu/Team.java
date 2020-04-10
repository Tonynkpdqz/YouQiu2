package com.example.tonyn.youqiu;

import cn.bmob.v3.BmobObject;

/**
 * Created by tonyn on 2017/3/13.
 */

public class Team extends BmobObject {
    private String teamname;
    private String teamer;

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getTeamer() {
        return teamer;
    }

    public void setTeamer(String teamer) {
        this.teamer = teamer;
    }
}
