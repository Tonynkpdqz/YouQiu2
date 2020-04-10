package com.example.tonyn.youqiu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Main5Activity extends AppCompatActivity {

    private EditText teamname;
    private EditText teamplayer;
    private String team;
    private String tp;
    private ActionCollor actionCollor;
    private Team myteam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        teamname = (EditText) findViewById(R.id.editText9);
        teamplayer = (EditText) findViewById(R.id.editText10);
        myteam = new Team();
        actionCollor = new ActionCollor();
        ActionCollor.addlist(this);
    }

    public void duoRen(View view){
        team = teamname.getText().toString();
        tp = teamplayer.getText().toString();
        myteam.setTeamname(team);
        myteam.setTeamer(tp);
        myteam.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null){
                    Toast.makeText(Main5Activity.this,"报名成功！请等待我们的消息",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Main5Activity.this,"报名失败！请稍后重试（是不是报过了？）",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
