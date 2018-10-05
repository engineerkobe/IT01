package com.itheima.service.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.itheima.service.bean.UserInfo;
import com.itheima.service.config.Config;
import com.mysql.jdbc.PreparedStatement;

public class GetUser {

	public static UserInfo getUserInfo(String inputUserName,String inputpwd){

		 Connection connection = null;
	        Statement stmt = null;
	        String sql = null;

	        try {
	            String databaseName = "book";
	            String host = Config.IP;
	            String port = "3306";
	            String username = "root"; //�û�AK
	            String password = "wt0918915535";
	            String driverName = "com.mysql.jdbc.Driver";
	            String dbUrl = "jdbc:mysql://";
	            String serverName = host + ":" + port + "/";
	            String SSL = "?autoReconnect=true&useSSL=false";
	            String connName = dbUrl + serverName + databaseName + SSL;;
	            //註冊驅動
	            Class.forName(driverName);
	            //創建連結URL 
	            connection = DriverManager.getConnection(connName, username,
	                    password);
	            //開始連線
	            stmt = connection.createStatement();
	            //取得所有書的資料
	            sql = "select * from book.userinfo";
	            //System.out.println("com.itheima.service.user:"+sql);
	            ResultSet rss = stmt.executeQuery(sql);
	            /*
	             * 資料表|"ISBN"|"NAME"|"COVER"|"Borrower"|
	             * ----|------|------|-------|----------|
	             * 	    number text	  Blob	  Text
	            */
	            if(rss!=null) {
					while(rss.next()) {
						System.out.println("com.itheima.service.user:"+rss.getString(3));
						  if(rss.getString(2).equals(inputUserName) && rss.getString(3).equals(inputpwd)){
								UserInfo userinfo = new UserInfo();
								String OGR_FID = rss.getString(1);
								String USERNAME = rss.getString(2);
								String PASSWORD = rss.getString(3);
								String BORROWING = rss.getString(4);
								
								//book.setOGR_FID(OGR_FID);
								userinfo.setUSERNAME(USERNAME);
								userinfo.setPASSWORD(PASSWORD);
								userinfo.setBORROWING(BORROWING);
								return userinfo;
				  }
	            }
	        }
	            
//	            response.getOutputStream().write((execute+"").getBytes());
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        return null;
		
	}
	
}
