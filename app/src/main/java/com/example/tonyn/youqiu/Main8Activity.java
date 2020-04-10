package com.example.tonyn.youqiu;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main8Activity extends Activity {

    ActionCollor actionCollor = new ActionCollor();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        Handler handler = new Handler();
        ActionCollor.addlist(this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Main8Activity.this,Main2Activity.class);
                startActivity(intent);
            }
        },3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
