package com.itheima.service.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.itheima.service.bean.NewsBean;

public class NewsDao {

	public static ArrayList<NewsBean> getNews(){

		 Connection connection = null;
	        Statement stmt = null;
	        ResultSet rs = null;
	        String sql = null;

	        try {
	            /*****��д���ݿ������Ϣ(��������ݿ�����ҳ)*****/
	            String databaseName = "demosql";
	            String host = "192.168.43.213";
	            String port = "3306";
	            String username = "root"; //�û�AK
	            String password = "wt0918915535";
	            String driverName = "com.mysql.jdbc.Driver";
	            String dbUrl = "jdbc:mysql://";
	            String serverName = host + ":" + port + "/";
	            String SSL = "?autoReconnect=true&useSSL=false";
	            String connName = dbUrl + serverName + databaseName + SSL;;

	           
	            Class.forName(driverName);
	            connection = DriverManager.getConnection(connName, username,password);
				if(connection != null && !connection.isClosed()) 
	                System.out.println("資料庫連線測試成功！"); 
				
	            stmt = connection.createStatement();
	           
	            sql = "select * from book";
	            ResultSet rss = stmt.executeQuery(sql);
	            ArrayList<NewsBean> arrayList = new ArrayList<NewsBean>();
	            if(rss != null){
	            	while(rss.next()){
	            	/*	
	            		int BOOK_ID = rss.getInt("BOOK_ID");
	            		String BOOK_NAME = rss.getString("BOOK_NAME");
	            		String WRITER = rss.getString("WRITER");
	            		String OUTPUT = rss.getString("OUTPUT");
	            		String PRICE = rss.getString("PRICE");
	            	*/	
	            		
	            		int BOOK_ID = rss.getInt(1);
	            		String BOOK_NAME = rss.getString(3);
	            		String WRITER = rss.getString(4);
	            		String OUTPUT = rss.getString(5);
	            		String PRICE = rss.getString(6);
	            		NewsBean newsBean = new NewsBean();
	            		newsBean.setBOOK_ID(BOOK_ID);
	            		newsBean.setBOOK_NAME(BOOK_NAME);
	            		newsBean.setWRITER(WRITER);
	            		newsBean.setOUTPUT(OUTPUT);
	            		newsBean.setPRICE(PRICE);
	            		arrayList.add(newsBean);
	            	}
	            	return arrayList;
	            	
	            }
	            
//	            response.getOutputStream().write((execute+"").getBytes());
	        } catch (Exception e) {
	        }
	        return null;
		
	}
	
}
