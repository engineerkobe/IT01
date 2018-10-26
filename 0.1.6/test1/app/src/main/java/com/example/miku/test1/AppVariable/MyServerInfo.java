package com.example.miku.test1.AppVariable;

public class MyServerInfo {
    public static String ip = "192.168.43.166";
    public static String port = "8080";
    public static String getUrl() {
        return ip+":"+port;
    }
}
