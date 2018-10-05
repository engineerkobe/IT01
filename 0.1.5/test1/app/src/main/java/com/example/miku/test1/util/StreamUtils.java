package com.example.miku.test1.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamUtils {
    public static String streamToString(InputStream in ){
        String result = "";
        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length = 0;
            while((length = in.read(buffer)) != -1) {
                out.write(buffer,0,length);
                out.flush();
            }
            result = out.toString();
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static byte[] streamToByte(InputStream in ){
        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length = 0;
            while((length = in.read(buffer)) != -1) {
                out.write(buffer,0,length);
                //out.flush();
            }
            return  out.toByteArray();
            //out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
