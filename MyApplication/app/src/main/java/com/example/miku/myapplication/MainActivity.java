package com.example.miku.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miku.myapplication.util.UserInfoUtil;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    private CheckBox cb_rem;
    private Button bt_login;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.input_password);
        cb_rem = (CheckBox) findViewById(R.id.cb_rem);
        bt_login = (Button) findViewById(R.id.bt_Login);
        //b.設置按鈕的點擊事件
        bt_login.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                login();
            }
        });
        Map<String,String> hashMap =  UserInfoUtil.getUserInfo();
        if(hashMap != null) {
            String username = hashMap.get("username");
            String password = hashMap.get("password");
            et_username.setText(username);
            et_password.setText(password);
        }
    }
    private void login() {
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        boolean isrem = cb_rem.isChecked();
        //判斷用戶銘是否為空，不為空請求服務氣
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(mContext,"用戶密碼不得為空",Toast.LENGTH_SHORT).show();
        }
        //判斷是否記住密馬
        if(isrem){
            boolean result = UserInfoUtil.saveUserInfo(username,password);
            if(result) {
                Toast.makeText(mContext, "用戶名密碼保存成功", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(mContext, "用戶名密碼保存失敗", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(mContext, "不保存帳號密碼", Toast.LENGTH_SHORT).show();
        }
    }
}
