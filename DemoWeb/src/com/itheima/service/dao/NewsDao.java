package com.itheima.service.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.itheima.service.bean.Book;

public class NewsDao {

	public static ArrayList<Book> getBooks(){

		 Connection connection = null;
	        Statement stmt = null;
	        ResultSet rs = null;
	        String sql = null;

	        try {
	            String databaseName = "book";
	            String host = "192.168.43.213";
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
	            sql = "select * from book";
	            ResultSet rss = stmt.executeQuery(sql);
	            /*
	             * 資料表|"ISBN"|"NAME"|"COVER"|"Borrower"|
	             * ----|------|------|-------|----------|
	             * 	    number text	  Blob	  Text
	            */
	            ArrayList<Book> arrayList = new ArrayList<Book>();
	            if(rss != null){
	            	while(rss.next()){
	            		Book book = new Book();
	            		String OGR_FID = rss.getString(1);
	            		String ISBN = rss.getString(2);
	            		String NAME = rss.getString(3);
	            		String COVER = rss.getString(4);
	            		String BORROWER = rss.getString(5);
	            		
	            		//book.setOGR_FID(OGR_FID);
	            		book.setISBN(ISBN);
	            		book.setNAME(NAME);
	            		book.setCOVER(COVER);
	            		book.setBORROWER(BORROWER);
	            		arrayList.add(book);
	            	}
	            	return arrayList;
	            	
	            }
	            
//	            response.getOutputStream().write((execute+"").getBytes());
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        return null;
		
	}
	
}
