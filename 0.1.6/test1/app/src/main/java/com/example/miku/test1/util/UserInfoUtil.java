package com.example.miku.test1.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class UserInfoUtil {

    public static boolean saveUserInfo(Context context, String username, String password) {
        try {
            String userinfo = username + "," + password; //
//            String path = "/data/data/com.example.miku.myapplication/";
            //讀取在自己的私有目錄
            String path = context.getFilesDir().getPath();
            File file = new File(path, "userinfo.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(userinfo.getBytes());
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Map<String, String> getUserInfo(Context context) {
        try {
//          String userinfo = username + "," + password;
            //保存在自己的私有目錄
            String path = context.getFilesDir().getPath();
//            String path = "/data/data/com.example.miku.myapplication/";
            File file = new File(path, "userinfo.txt");
            FileInputStream fileInpurStream = new FileInputStream(file);
            BufferedReader buffereReader = new BufferedReader(new InputStreamReader(fileInpurStream));
            String readLine = buffereReader.readLine();
            String[] split = readLine.split(",");
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("username", split[0]);
            hashMap.put("password", split[1]);
            fileInpurStream.close();
            buffereReader.close();
            return hashMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
