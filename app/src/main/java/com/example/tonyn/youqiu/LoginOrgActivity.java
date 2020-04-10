package com.example.tonyn.youqiu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class LoginOrgActivity extends AppCompatActivity{

    private Button Done;
    private EditText et;
    private String creater;
    private String createName;
    private Organization organization;
    private freshLinster linster;
    private BmobQuery<Organization> bmobQuery;
    private List<String> orgList = new ArrayList<>();
    //Boolean isLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_org);
        Intent intent = getIntent();
        bmobQuery = new BmobQuery<>();
        query();
        Log.d("zhecimeiwentileba", "onCreate: "+orgList.size());
        organization = new Organization();
        creater = intent.getStringExtra("creater");
        Done = (Button) findViewById(R.id.buttondone);
        et = (EditText) findViewById(R.id.createtext);
        ActionCollor.addlist(this);
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createName = et.getText().toString();
                organization.setOrgName(createName);
                organization.setOrgPerson(creater);
                bmobQuery.addQueryKeys("orgName");

                //query();
                /*bmobQuery.findObjects(new FindListener<Organization>() {
                    @Override
                    public void done(List<Organization> list, BmobException e) {
                        if (list != null){
                            //Log.d("zhelichuxianlema???", "done: "+list.toString());
                            for (Organization o:list){
                                String s = o.getOrgName();
                                orgList.add(s);
                            }
                            Log.d("KNAKNAKNAKNAKNA", "done: "+orgList.size());
                        }else{
                            Log.d("LOOOOOOOOOKKKK", "done: ");
                        }
                    }
                });*/
                Log.d("THIS", "onClick: "+orgList.size());
                for (int i = 0; i < orgList.size(); i++){
                    Log.d("LLOOKKAATTTTHHIISS", "onClick: "+orgList.get(i));
                }


                    if (orgList.size() == 0){
                        Log.d("MISTAKEEEEEEEEEEEEEEEEE", "onClick: "+orgList.size());
                        //错误在这里
                        organization.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null){
                                    Toast.makeText(LoginOrgActivity.this,"你已经成功建立起属于你的足球社区啦",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginOrgActivity.this,MainActivity.class);
                                    startActivity(intent);
                                }else{
                                    Log.d("LLLLOOOOOKKKKAAAATTTT", "done: "+e.getErrorCode()+e.toString());
                                    Toast.makeText(LoginOrgActivity.this,"出错！请稍后重试（检查网络是否有问题）",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else{
                        for (int i = 0; i < orgList.size(); i++){
                            if (orgList.get(i).equals(createName)){
                                Toast.makeText(LoginOrgActivity.this,"此社区名已经被注册",Toast.LENGTH_SHORT).show();
                                //isLogin = true;
                                break;
                            }
                            if (i==orgList.size()-1){
                                organization.save(new SaveListener<String>() {
                                    @Override
                                    public void done(String s, BmobException e) {
                                        if (e == null){
                                            Toast.makeText(LoginOrgActivity.this,"你已经成功建立起属于你的足球社区啦",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(LoginOrgActivity.this,MainActivity.class);
                                            startActivity(intent);
                                        }else{
                                            Log.d("LLLLOOOOOKKKKAAAATTTT", "done: "+e.getErrorCode()+e.toString());
                                            Toast.makeText(LoginOrgActivity.this,"出错！请稍后重试（检查网络是否有问题）",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                break;
                            }
                        }
                    }

//                if (!isLogin){
//                    organization.save(new SaveListener<String>() {
//                        @Override
//                        public void done(String s, BmobException e) {
//                            if (e == null){
//                                Toast.makeText(LoginOrgActivity.this,"你已经成功建立起属于你的足球社区啦",Toast.LENGTH_SHORT).show();
//                            }else{
//                                Log.d("LLLLOOOOOKKKKAAAATTTT", "done: "+e.getErrorCode()+e.toString());
//                                Toast.makeText(LoginOrgActivity.this,"出错！请稍后重试（检查网络是否有问题）",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                }

            }


        });
    }

    public void query(){
        bmobQuery.findObjects(new FindListener<Organization>() {
            @Override
            public void done(List<Organization> list, BmobException e) {
                if (list != null){
                    //Log.d("zhelichuxianlema???", "done: "+list.toString());
                    orgList.clear();
                    for (Organization o:list){
                        String s = o.getOrgName();
                        orgList.add(s);
                    }
                    Log.d("KNAKNAKNAKNAKNA", "done: "+orgList.size());
                }else{
                    Log.d("LOOOOOOOOOKKKK", "done: ");
                }
            }
        });
    }


    public LoginOrgActivity(freshLinster linster){
        this.linster = linster;
    }

    public LoginOrgActivity(){}

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
