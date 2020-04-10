package com.example.tonyn.youqiu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class Main2Activity extends AppCompatActivity {

    private Button login;
    private Button register;
    private EditText login_text;
    private EditText password;
    private TextView logintext;
    private TextView passwordtext;
    private Person p2;
    private BmobUser b2;
    private CheckBox checkBox;
    boolean isRemember = false;
    private BmobQuery<Person> bmobQuery;
    private ActionCollor actionCollor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        .setApplicationId("0fc82ac1c3cfcfbb1a97a9aca0f3e9d4")
        ////请求超时时间（单位为秒）：默认15s
        .setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        .setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        .setFileExpiration(2500)
        .build();
        Bmob.initialize(config);
        login = (Button) findViewById(R.id.button_login);
        register = (Button) findViewById(R.id.button_register);
        login_text = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isRemember = true;
                }else{
                    isRemember = false;
                }
            }
        });
        //logintext = (TextView) findViewById(R.id.textView2);
        //passwordtext = (TextView) findViewById(R.id.textView3);
        p2 = new Person();
        b2 = BmobUser.getCurrentUser();
        if (isRemember) {
            if (b2 != null) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                intent.putExtra("name", b2.getUsername());
                //intent.putExtra("password", p2.getPassword());
                startActivity(intent);
            } else {
                Toast.makeText(Main2Activity.this, "账号已过期，请重新登陆", Toast.LENGTH_SHORT).show();
                b2 = new BmobUser();
            }
        }
            bmobQuery = new BmobQuery<>();
            ActionCollor.addlist(this);
    }

    public void login(View view){


        final String name = login_text.getText().toString();
        final String password_text = password.getText().toString();
        b2.setUsername(name);
        b2.setPassword(password_text);
        b2.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser user, BmobException e) {
                if (e == null){
                    Toast.makeText(Main2Activity.this,"登陆成功！",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                    intent.putExtra("name", b2.getUsername());
                    startActivity(intent);

                }else{
                    Toast.makeText(Main2Activity.this,"用户名或密码错误！",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
//        bmobQuery.getObject(name, new QueryListener<Person>() {
//            @Override
//            public void done(Person person, BmobException e) {
//                if (e != null){
//                    if (person.getUsername().equals(name)&&person.getPassword().equals(password_text)){
//                        Toast.makeText(Main2Activity.this,"登陆成功！",Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
//                        intent.putExtra("username",name);
//                        intent.putExtra("password",password_text);
//                        startActivity(intent);
//                    }else{
//                        Toast.makeText(Main2Activity.this,"此用户名尚未注册或密码输入错误",Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                }else{
//                    Toast.makeText(Main2Activity.this,"因系统原因登录失败！请重试",Toast.LENGTH_SHORT).show();
//                    Log.d("LOOKATTHIS", "done: "+e.getErrorCode());
//                }
//            }
//        });
    }

    public void register(View view){
        Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
        startActivity(intent);
    }

}
