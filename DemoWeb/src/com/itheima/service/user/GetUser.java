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
		String databaseName = Config.DB;
		String host = Config.IP;
		String port = "3306";
		String username = "miku"; //�û�AK
		String password = "rock0800";
		String driverName = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://";
		String serverName = host + ":" + port + "/";
		String SSL = "?autoReconnect=true&useSSL=false";
		String connName = dbUrl + serverName + databaseName + SSL;;
		try {
			//註冊驅動
			Class.forName(driverName);
			//創建連結URL 
			connection = DriverManager.getConnection(connName, username,
					password);
			//開始連線
			stmt = connection.createStatement();
			//取得所有書的資料
			sql = "SELECT * FROM demo2.userinfo";
			//System.out.println("com.itheima.service.user:"+sql);
			ResultSet rss = stmt.executeQuery(sql);
			/*
			 *  +---------+----------+--------------+------+
			 *	| account | password | head_sticker | name |
			 *	+---------+----------+--------------+------+
			 *	| root    | 123      | NULL         | MIKU |
			 *	| test1   | test1    | NULL         | NULL |
			 *	+---------+----------+--------------+------+
			 */
			if(rss!=null) {
				while(rss.next()) {
					  if(rss.getString(1).equals(inputUserName) && rss.getString(2).equals(inputpwd)){
							System.out.println("com.itheima.service.user:"+rss.getString(1));
							UserInfo userinfo = new UserInfo();
							
							String headSticker = rss.getString(3);
							String name = rss.getString(4);
							
							userinfo.setmHeadSticker(headSticker);
							userinfo.setmName(name);
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
