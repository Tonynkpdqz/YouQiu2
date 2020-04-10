package com.example.tonyn.youqiu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class EveryOrgActivity extends AppCompatActivity {

    private List contentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_org);
        Intent intent = getIntent();
        contentList = intent.getStringArrayListExtra("contentlist");
    }


}
