package com.itheima.service.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.itheima.service.bean.Book;
import com.itheima.service.config.Config;

public class NewsDao {

	static	Connection connection = null;
	static	Statement stmt = null;
	static	ResultSet rs = null;
	static	String sql = null;
	static	String databaseName = "book";
	static	String host = Config.IP;
	static	String port = "3306";
	static	String username = "root"; //�û�AK
	static	String password = "wt0918915535";
	static	String driverName = "com.mysql.jdbc.Driver";
	static	String dbUrl = "jdbc:mysql://";
	static	String serverName = host + ":" + port + "/";
	static	String SSL = "?autoReconnect=true&useSSL=false";
	static	String connName = dbUrl + serverName + databaseName + SSL;
	
	public static ArrayList<Book> getBooks(){
	        try {
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
	            		String BOOKCASE = rss.getString(6);
	            		
	            		//book.setOGR_FID(OGR_FID);
	            		book.setISBN(ISBN);
	            		book.setNAME(NAME);
	            		book.setCOVER(COVER);
	            		book.setBORROWER(BORROWER);
	            		book.setBOOKCASE(BOOKCASE);
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
	
	public static Book getBook(String searchISBN,String searchUID){
	        try {
	            //註冊驅動
	            Class.forName(driverName);
	            //創建連結URL 
	            connection = DriverManager.getConnection(connName, username,
	                    password);
	            //開始連線
	            stmt = connection.createStatement();
	            //取得所有書的資料
	            sql = "select * from book";
//	            if(!searchISBN.equals("null")) 
//	            	sql = "select * from book where ISBN = " + searchISBN ;
	            if(!searchUID.equals("null")) 
	            	sql = "select * from book where bookRFID = '" + searchUID  +"'";
	            	
	            ResultSet rss = stmt.executeQuery(sql);
	            /*
	             * 資料表|"ISBN"|"NAME"|"COVER"|"Borrower"|
	             * ----|------|------|-------|----------|
	             * 	    number text	  Blob	  Text
	            */
	            if(rss != null){
	            	while(rss.next()){
	            		Book book = new Book();
	            		String OGR_FID = rss.getString(1);
	            		String ISBN = rss.getString(2);
	            		String NAME = rss.getString(3);
	            		String COVER = rss.getString(4);
	            		String BORROWER = rss.getString(5);
	            		String BOOKCASE = rss.getString(6);
	            		
	            		//book.setOGR_FID(OGR_FID);
	            		book.setISBN(ISBN);
	            		book.setNAME(NAME);
	            		book.setCOVER(COVER);
	            		book.setBORROWER(BORROWER);
	            		book.setBOOKCASE(BOOKCASE);
	            		return book ;
	            	}
	            }
	            
//	            response.getOutputStream().write((execute+"").getBytes());
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        return null;
	}
	
}
