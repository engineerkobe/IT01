package com.example.miku.test1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miku.test1.util.StreamUtils;
import com.example.miku.test1.util.UserInfoUtil;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
        Map<String,String> hashMap =  UserInfoUtil.getUserInfo(mContext);
        if(hashMap != null) {
            String username = hashMap.get("username");
            String password = hashMap.get("password");
            et_username.setText(username);
            et_password.setText(password);
            cb_rem.setChecked(true);
        }

    }
    private void login() {
        final String username = et_username.getText().toString().trim();
        final String password = et_password.getText().toString().trim();
        final boolean isrem = cb_rem.isChecked();
        //判斷用戶銘是否為空，不為空請求服務氣
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(mContext,"用戶密碼不得為空",Toast.LENGTH_SHORT).show();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                final boolean issuccess = requestNetForGetLogin(username,password);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(issuccess) {
                            //登入成功
                            //判斷是否記住密馬
                            if(isrem){
                                boolean result = UserInfoUtil.saveUserInfo(mContext,username,password);
                                if(result) {
                                    Toast.makeText(mContext, "登入成功，用戶名密碼保存成功", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(mContext, "登入成功，用戶名密碼保存失敗", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(mContext, "不保存帳號密碼", Toast.LENGTH_SHORT).show();
                            }
//                            切換畫面
                            Intent intent = new Intent(MainActivity.this, FragmentMain.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(mContext, "登入失敗", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }

     private boolean requestNetForGetLogin(String username,String password) {
        try {
            //用urlconnection 請求服務器將用戶密碼發送服務氣驗證
            URL url = new URL("http://192.168.43.166:8080/DemoWeb/LoginServlet?username="+username+"&pwd="+password);

            HttpURLConnection openConnection  = (HttpURLConnection) url.openConnection();
            openConnection.setRequestMethod("GET");
            openConnection.setConnectTimeout(10*1000);

            int code = openConnection.getResponseCode();
            if(code == 200) {
                InputStream inputStream = openConnection.getInputStream();
                String result = StreamUtils.streamToString(inputStream);

                if(result.contains("success")){
                    return true;
                }
            }
        }catch(Exception E) {
            E.printStackTrace();
        }
         return false;
    }
/*
    private boolean requestNetForGetLogin(String username,String password) {
        try {
            //用urlconnection 請求服務器將用戶密碼發送服務氣驗證
            URL url = new URL("http://192.168.43.166:8080/DemoWeb/LoginServlet?username="+username+"&pwd="+password);

            HttpURLConnection openConnection  = (HttpURLConnection) url.openConnection();
            openConnection.setRequestMethod("POST");
            openConnection.setConnectTimeout(10*1000);
            openConnection.setRequestProperty("Content-Lengeth","21");
            openConnection.setRequestProperty("Cache-Control","max-age=0");
            openConnection.setRequestProperty("Origin","21");
            openConnection.setRequestProperty("Content-Lengeth","21");
            openConnection.setDoOutput(true);
            openConnection.getOutputStream().write(("username="+username+"&pwd="+password).getBytes());

            int code = openConnection.getResponseCode();
            if(code == 200) {
                InputStream inputStream = openConnection.getInputStream();
                String result = StreamUtils.streamToString(inputStream);
                System.out.println(result);
                if(result.contains("success")){
                    return true;
                }
            }
        }catch(Exception E) {
            E.printStackTrace();
        }
        return false;
    }
    */
}
