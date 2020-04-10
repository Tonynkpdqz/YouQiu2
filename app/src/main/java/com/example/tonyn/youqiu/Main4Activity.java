package com.example.tonyn.youqiu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Main4Activity extends AppCompatActivity {

    private EditText playername;
    private EditText school;
    private EditText phonenumber;
    private Player player;
    private String name;
    private String myschool;
    private String myphone;
    private ActionCollor actionCollor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        playername = (EditText) findViewById(R.id.editText5);
        school = (EditText) findViewById(R.id.editText7);
        phonenumber = (EditText) findViewById(R.id.editText8);
        player = new Player();
        actionCollor = new ActionCollor();
        ActionCollor.addlist(this);
    }

    public void simple(View view){
        name = playername.getText().toString();
        myschool = school.getText().toString();
        myphone = phonenumber.getText().toString();
        player.setMyname(name);
        player.setMyschool(myschool);
        player.setPhonenumber(myphone);
        player.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null){
                    Toast.makeText(Main4Activity.this,"您已成功报名！请等待我们的通知！",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Main4Activity.this,"报名失败，请稍后重试！(您是否已经报过名？)",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void team(View view){
        name = playername.getText().toString();
        myschool = school.getText().toString();
        myphone = phonenumber.getText().toString();
        player.setMyname(name);
        player.setMyschool(myschool);
        player.setPhonenumber(myphone);
        player.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null){
                }else {
                    Toast.makeText(Main4Activity.this,"报名失败，请稍后重试！(您是否已经报过名？)",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Intent intent = new Intent(Main4Activity.this,Main5Activity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main4,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.quit:
                ActionCollor.finashall();
                break;
        }
        return true;
    }


}
