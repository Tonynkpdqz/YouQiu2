package com.example.tonyn.youqiu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class Main3Activity extends AppCompatActivity {

    private Button queRen;
    private EditText userName;
    private EditText passWord;
    private EditText email;
    private BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
    private Person p1 = new Person();
    private Person2 p2 = new Person2();
    ActionCollor actionCollor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        userName = (EditText) findViewById(R.id.editText3);
        passWord = (EditText) findViewById(R.id.editText4);
        email = (EditText) findViewById(R.id.editText6);
        actionCollor = new ActionCollor();
        ActionCollor.addlist(this);
    }

    public void queRen(View view){
        final String name = userName.getText().toString();
        String pass = passWord.getText().toString();
        String emailtext = email.getText().toString();
        if(name.equals("")||pass.equals("")){
            Toast.makeText(Main3Activity.this,"用户名/密码不能为空哦",Toast.LENGTH_SHORT).show();
            return;
        }
        p2.setUsername(name);
        p2.setPassword(pass);
        p2.setEmail(emailtext);
        p2.signUp(new SaveListener<Object>() {
            @Override
            public void done(Object o, BmobException e) {
                if (e == null){
                    Toast.makeText(Main3Activity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Main3Activity.this,Main2Activity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Main3Activity.this,"此用户名或此邮箱被注册过了哦",Toast.LENGTH_SHORT).show();
                }
            }
        });
//        bmobQuery.getObject(name, new QueryListener<Person>() {
//            @Override
//            public void done(Person person, BmobException e) {
//                if (e == null){
//                    if (person.getUsername().equals(name)){
//                        Toast.makeText(Main3Activity.this,"此用户名以存在，请重新注册",Toast.LENGTH_SHORT).show();
//                        return;
//                    }else{
//                        p1.save(new SaveListener<String>() {
//                            @Override
//                            public void done(String s, BmobException e) {
//                                if (e == null){
//                                    s = p1.getUsername();
//                                    Toast.makeText(Main3Activity.this,"注册成功！",Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(Main3Activity.this,Main2Activity.class);
//                                    startActivity(intent);
//                                }else{
//                                    Toast.makeText(Main3Activity.this,"数据上传失败，错误信息："+e.getMessage(),Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//                    }
//                }else{
//                    Toast.makeText(Main3Activity.this,"因系统原因注册失败！请重试",Toast.LENGTH_SHORT).show();
//                    Log.d("LOOKKAATTHIS", "done: "+e.getErrorCode());
//                }
//            }
//        });
    }
}