package com.itheima.service.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itheima.service.bean.Article;
import com.itheima.service.bean.Book;
import com.itheima.service.config.Config;

public class MyDB {

	static	Connection connection = null;
	static	Statement stmt = null;
	static	String sql = null;
	static	String databaseName = Config.DB;
	static	String host = Config.IP;
	static	String port = "3306";
	static	String username = "miku"; //�û�AK
	static	String password = "rock0800";
	static	String driverName = "com.mysql.jdbc.Driver";
	static	String dbUrl = "jdbc:mysql://";
	static	String serverName = host + ":" + port + "/";
	static	String SSL = "?autoReconnect=true&useSSL=false";
	static	String connName = dbUrl + serverName + databaseName + SSL;
	
	public static ArrayList<Article> getArticleList() throws ClassNotFoundException, SQLException {
		//註冊驅動
		Class.forName(driverName);
		//創建連結URL 
		connection = DriverManager.getConnection(connName, username,
				password);
		stmt = connection.createStatement();
		
		sql = String.format("SELECT * FROM %s" , Config.ARTICLE);
		ResultSet resultset = stmt.executeQuery(sql);
		ArrayList<Article> articleArrayList = new ArrayList<>();
		
		if(resultset != null) {
			while(resultset.next()) {
				Article article = new Article();
				String poster = resultset.getString(resultset.findColumn("poster"));
				int rootid = resultset.getInt(resultset.findColumn("rootid"));
				int pid = resultset.getInt(resultset.findColumn("pid"));
				String title = resultset.getString(resultset.findColumn("title"));
				String content = resultset.getString(resultset.findColumn("content"));
				Date pdate = resultset.getDate(resultset.findColumn("pdate"));
				
				article.setPoster(poster);
				article.setTitle(title);
				article.setRootid(rootid);
				article.setPid(pid);
				article.setContent(content);
				article.setPdate(pdate);
				
				articleArrayList.add(article);
			}
			return articleArrayList;
		}
	return null;
	}
	public static ArrayList<Book> getBookList() throws ClassNotFoundException, SQLException {
			//註冊驅動
			Class.forName(driverName);
			//創建連結URL 
			connection = DriverManager.getConnection(connName, username,
					password);
			stmt = connection.createStatement();
			//取得所有書的資料
			sql = String.format("SELECT * FROM demo2.isbn_book" );
			ResultSet rss = stmt.executeQuery(sql);
			/*
			 * +---------+--------------+------+-----+---------+-------+
				| Field   | Type         | Null | Key | Default | Extra |
				+---------+--------------+------+-----+---------+-------+
				| isbn    | varchar(255) | NO   | PRI | NULL    |       |
				| name    | varchar(255) | NO   |     | NULL    |       |
				| content | text         | NO   |     | NULL    |       |
				| count   | int(11)      | NO   |     | NULL    |       |
				+---------+--------------+------+-----+---------+-------+
			*/
			ArrayList<Book> arrayList = new ArrayList<Book>();
			if(rss != null){
				while(rss.next()){
					Book book = new Book();
					String isbn = rss.getString(rss.findColumn("isbn"));
					String name = rss.getString(rss.findColumn("name"));
					String content = rss.getString(rss.findColumn("content"));
					int count = rss.getInt(rss.findColumn("count"));
					
					//book.setOGR_FID(OGR_FID);
					book.setISBN(isbn);
					book.setName(name);
					book.setContent(content);
					book.setCount(count);
					arrayList.add(book);
				}
				return arrayList;
				
			}
			
		return null;
	}
	public static Book getBook(String searchISBN,String searchUID) throws SQLException, ClassNotFoundException{
		
		//註冊驅動
		Class.forName(driverName);
		//創建連結URL 
		connection = DriverManager.getConnection(connName, username,
				password);
		stmt = connection.createStatement();
		//取得所有書的資料
		sql = "select * from book";
//	            if(!searchISBN.equals("null")) 
//	            	sql = "select * from book where ISBN = " + searchISBN ;
		if(!searchUID.equals("null")) 
			sql = "select * from book where bookRFID = '" + searchUID  +"'";
			
		ResultSet rss = stmt.executeQuery(sql);
		/*
		 * +---------+--------------+------+-----+---------+-------+
			| Field   | Type         | Null | Key | Default | Extra |
			+---------+--------------+------+-----+---------+-------+
			| isbn    | varchar(255) | NO   | PRI | NULL    |       |
			| name    | varchar(255) | NO   |     | NULL    |       |
			| content | text         | NO   |     | NULL    |       |
			| count   | int(11)      | NO   |     | NULL    |       |
			+---------+--------------+------+-----+---------+-------+
		*/
		if(rss != null){
			while(rss.next()){
				Book book = new Book();
				return book ;
			}
		}
	return null;
	}
}
