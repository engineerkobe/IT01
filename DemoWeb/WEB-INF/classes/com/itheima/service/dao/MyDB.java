package com.itheima.service.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itheima.service.bean.Article;
import com.itheima.service.bean.Book;
import com.itheima.service.bean.Entity;
import com.itheima.service.bean.RFID;
import com.itheima.service.bean.UserInfo;
import com.itheima.service.config.Config;

public class MyDB {

	static Connection connection = null;
	static Statement stmt = null;
	static String sql = null;
	static String databaseName = Config.DB;
	static String host = Config.IP;
	static String port = "3306";
	static String username = "miku"; // �û�AK
	static String password = "rock0800";
	static String driverName = "com.mysql.jdbc.Driver";
	static String dbUrl = "jdbc:mysql://";
	static String serverName = host + ":" + port + "/";
	static String SSL = "?autoReconnect=true&useSSL=false";
	static String useUnicode = "&useUnicode=true&characterEncoding=UTF-8";
	static String connName = dbUrl + serverName + databaseName + SSL+ useUnicode;
	static void initMySQL() throws SQLException, ClassNotFoundException {
		// 註冊驅動
		Class.forName(driverName);
		if (connection == null || stmt == null) {
			// 創建連結URL
			connection = DriverManager.getConnection(connName, username, password);
			stmt = connection.createStatement();
		}
	}

	public static ArrayList<Article> getArticleList(int rootid) throws ClassNotFoundException, SQLException, ParseException {
		initMySQL();
		//取得所有文章
		sql = String.format("SELECT * FROM %s", Config.ARTICLE);
		ResultSet rss = stmt.executeQuery(sql);
		ArrayList<Article> articleArrayList = new ArrayList<>();
		if (rss != null) {
			while (rss.next()) {
				Article article = Entity.getArticle(rss);
				if(article.getRootid() == rootid) {
					articleArrayList.add(article);
				}
			}
			return articleArrayList;
		}
		return null;
	}
	public static ArrayList<Article> getArticleList() throws ClassNotFoundException, SQLException, ParseException {
		initMySQL();
		//取得所有文章
		sql = String.format("SELECT * FROM %s", Config.ARTICLE);
		ResultSet rss = stmt.executeQuery(sql);
		ArrayList<Article> articleArrayList = new ArrayList<>();
		if (rss != null) {
			while (rss.next()) {
				Article article = Entity.getArticle(rss);
				if(article.getPid() == 0)
				articleArrayList.add(article);
			}
			return articleArrayList;
		}
		return null;
	}

	public static ArrayList<Book> getBorrowedBookList(String name) throws ClassNotFoundException, SQLException {
		initMySQL();
		// 取得所有書的資料
		sql = String.format("SELECT * FROM %s WHERE borrower=\"%s\"", Config.RFID_BOOK,name);
		ResultSet rss = stmt.executeQuery(sql);
		ArrayList<RFID> arrayList = new ArrayList<>();
		ArrayList<Book> arrayBookList = new ArrayList<>();
		if (rss != null) {
			while (rss.next()) {
				RFID rfid = Entity.getRFID(rss);
				arrayList.add(rfid);
			}
			if(arrayList.size()==0)
				return null;
			else {
				for(int i=0; i< arrayList.size(); i++) {
					Book book = getBook(arrayList.get(i).getIsbn());
					arrayBookList.add(book);
				}
			}
			return arrayBookList;
		}

		return null;
	}
	
	public static ArrayList<Book> getBookList() throws ClassNotFoundException, SQLException {
		initMySQL();
		// 取得所有書的資料
		sql = String.format("SELECT * FROM demo2.isbn_book");
		ResultSet rss = stmt.executeQuery(sql);
		ArrayList<Book> arrayList = new ArrayList<Book>();
		if (rss != null) {
			while (rss.next()) {
				Book book = Entity.getBook(rss);
				arrayList.add(book);
			}
			return arrayList;
		}

		return null;
	}

	public static Book getBookForRFID(String searchUID) throws SQLException, ClassNotFoundException {

		initMySQL();
//		用RFID查找書籍
		sql = String.format("select * from %s where rfid = \"%s\"", Config.RFID_BOOK, searchUID);
		ResultSet rss = stmt.executeQuery(sql);
		if (rss != null) {
			while (rss.next()) {
				RFID rfid = Entity.getRFID(rss);
				return getBook(rfid.getIsbn());
			}
		}
		return null;
	}

	public static Book getBook(String searchUID) throws SQLException, ClassNotFoundException {
		initMySQL();
		//用isbn查找書籍
		sql = String.format("select * from %s where isbn  = \"%s\"", Config.ISBN_BOOK, searchUID);
		ResultSet rss = stmt.executeQuery(sql);
		if (rss != null) {
			while (rss.next()) {
				return Entity.getBook(rss);
			}
		}
		return null;
	}

	public static List<RFID>getRFIDList(String searchUID) throws SQLException, ClassNotFoundException {
		initMySQL();
		//查詢RFID卡的資料
		sql = String.format("select * from %s where isbn = \"%s\"", Config.RFID_BOOK, searchUID);
		ResultSet rss = stmt.executeQuery(sql);
		List<RFID> RFIDs = new ArrayList<>();
		if (rss != null) {
			while (rss.next()) {
				RFIDs.add(Entity.getRFID(rss));
			}
			return RFIDs;
		}
			return null;
	}
	public static RFID getRFID(String searchUID) throws SQLException, ClassNotFoundException {
		initMySQL();
		//查詢RFID卡的資料
		sql = String.format("select * from %s where rfid = \"%s\"", Config.RFID_BOOK, searchUID);
		ResultSet rss = stmt.executeQuery(sql);
		if (rss != null) {
			while (rss.next()) {
				return Entity.getRFID(rss);
			}
		}
			return null;
	}

	public static boolean returnBook(String argrfid, String bookCaseName) throws ClassNotFoundException, SQLException {
		//查詢這本書是否有登記RFID
		RFID rfid = getRFID(argrfid);
		if (rfid == null)
			return false;
		initMySQL();
		boolean isborrow = !(getBorrowLog(argrfid));
		//書被借走但是未還書
		if (isborrow) {
			String sql = "UPDATE %s SET return_time=NOW() " + "WHERE rfid=\"%s\"" + "  && return_time IS NULL";
			sql = String.format(sql, Config.BORROW_LOG, argrfid);
			stmt.executeUpdate(sql);

			sql = "UPDATE %s SET return_log=NOW(),borrower=\"0\",is_borrow=\"0\",book_case_name=\"%s\" " + "WHERE rfid=\"%s\"";
			sql = String.format(sql, Config.RFID_BOOK, bookCaseName, argrfid);
			stmt.executeUpdate(sql);
			return true;
		}
		return false;
	}

	public static String borrowBook(String argaccount, String argrfid,String millisTime) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		initMySQL();
		//查詢這本書是否有登記RFID
		RFID rfid = getRFID(argrfid);
		//查詢是否有這位使用者
		UserInfo userinfo = getUserInfo(argaccount);
		if (rfid == null || userinfo == null)
			return "Can't find";
		//查詢這本書是否已經被借走
		boolean isborrow = getBorrowLog(argrfid);
		boolean b = getBorrowTime(argrfid,millisTime);
		
		if (isborrow & b) {
			// 紀錄借書的時間
			sql = "INSERT INTO %s VALUES (\"%s\",\"%s\",NOW(),NULL)";
			sql = String.format(sql, Config.BORROW_LOG, argrfid, argaccount);
			stmt.execute(sql);
			// rfid卡綁定帳號
			sql = "UPDATE %s "
					+ "SET is_borrow=1,borrower=\"%s\"" +","
//					+ "log=NOW()" + ","
					+ "book_case_name=\"NULL\"" + " "
					+ "WHERE rfid=\"%s\"";
			sql = String.format(sql, Config.RFID_BOOK, argaccount, argrfid);
			stmt.executeUpdate(sql);
			return "Borrow success";
		} else if(!isborrow) {
			//如果被借走了
			return "Borrowed";
		}
		else
		{
			//已經還書但是沒有再掃描
			return "Scanning once";
		}
	}
	
	private static boolean getBorrowTime(String argrfid,String millisTime) throws ClassNotFoundException, SQLException {
		
		// TODO Auto-generated method stub initMySQL();
		// 查找借書紀錄
		// return_time IS NULL 代表還沒還書
		sql = String.format("SELECT * FROM %s WHERE " + "rfid=\"%s\" ",
				Config.RFID_BOOK, argrfid);
		ResultSet rss = stmt.executeQuery(sql);
		if (rss != null) {
			while (rss.next()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
				int borrow_msIndex = rss.findColumn("return_log");
//				int Index = rss.findColumn("borrow_ms");
				//取得時間的字串
				String datetime= rss.getString(borrow_msIndex);
				//字串轉成date
				Date date =null;
				try {
					date = sdf.parse(datetime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date inputDate = new Date(Long.parseLong(millisTime));
//				if(millisTime.equals(borrow_ms))
				System.out.println("log:"+date.toString()+"  inLog: "+inputDate.toString());
				
				if(inputDate.compareTo(date) < 0)
					return false;
			}
		}
		return true;
	}
	private static boolean getBorrowLog(String argrfid) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub initMySQL();
		// 查找借書紀錄
		// return_time IS NULL 代表還沒還書
		sql = String.format("SELECT * FROM %s WHERE " + "rfid=\"%s\" && " + "return_time IS NULL" + "",
				Config.BORROW_LOG, argrfid);
		ResultSet rss = stmt.executeQuery(sql);
		if (rss != null) {
			while (rss.next()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * 查詢使用者是否存在
	 * 
	 * @param inputUserName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static UserInfo getUserInfo(String inputUserName) throws SQLException, ClassNotFoundException {
		initMySQL();
		
		sql = "SELECT * FROM demo2.userinfo";
		ResultSet rss = stmt.executeQuery(sql);
		if (rss != null) {
			while (rss.next()) {
				UserInfo userinfo = Entity.getUserInfo(rss);
				if (userinfo.getAccount().equals(inputUserName))
					return userinfo;
			}
		}
		return null;
	}

			
	public static int setUserName(String account, String name) {
		try {
			initMySQL();
			
			String sql = "UPDATE userinfo SET name=\"%s\" WHERE account=\"%s\"";
			sql = String.format(sql,name,account);
//			受影響的資料有幾筆
			return stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static UserInfo getUserInfo(String inputUserName, String inputpwd) {
		try {
			initMySQL();
			// 取得所有書的資料
			sql = "SELECT * FROM demo2.userinfo";
			ResultSet rss = stmt.executeQuery(sql);
			
			if (rss != null) {
				while (rss.next()) {
					UserInfo userinfo = Entity.getUserInfo(rss);
					if (userinfo.getAccount().equals(inputUserName) && userinfo.getPassword().equals(inputpwd)) {
						return userinfo;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//新增使用用者
	public static void addUserInfo(String account, String password2) {
		// TODO Auto-generated method stub
		try {
			initMySQL();
			sql = "INSERT INTO demo2.userinfo VALUES( \"%s\",\"%s\",null)";
			sql = String.format(sql, account, password2);
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addArticle(String account, String title, String content) {
		// TODO Auto-generated method stub
//		+---------+--------------+------+-----+---------+-------+  
//		| Field   | Type         | Null | Key | Default | Extra |  
//		+---------+--------------+------+-----+---------+-------+  
//		| poster  | varchar(255) | YES  | MUL | NULL    |       |  
//		| rootid  | int(11)      | YES  |     | NULL    |       |  
//		| pid     | int(11)      | YES  |     | NULL    |       |  
//		| title   | varchar(255) | YES  |     | NULL    |       |  
//		| content | text         | YES  |     | NULL    |       |  
//		| pdate   | datetime     | YES  |     | NULL    |       |  
//		+---------+--------------+------+-----+---------+-------+  	
		int roodid = 0;
		try {
			initMySQL();
			sql = "SELECT MAX(rootid) FROM demo2.article";
			ResultSet rss = stmt.executeQuery(sql);
			if(rss != null) {
				//找最大的roodid
				while(rss.next()) {
					roodid = rss.getInt(rss.findColumn("MAX(rootid)"));
					roodid++;
				}
			}
			sql = "INSERT INTO article VALUES (\"%s\",%s,%s,\"%s\",\"%s\",%s)";
			sql = String.format(sql,account,
					String.valueOf(roodid),
					"0",title,content,"NOW()");
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void addArticle(String account, String title, String content,String rootid) {
		// TODO Auto-generated method stub
		int pid = 1;
		try {
			initMySQL();
			sql = "SELECT MAX(pid) FROM demo2.article";
			ResultSet rss = stmt.executeQuery(sql);
			if(rss != null) {
				//找最大的roodid
				while(rss.next()) {
					pid = rss.getInt(rss.findColumn("MAX(pid)"));
					pid++;
				}
			}
			sql = "INSERT INTO article VALUES (\"%s\",%s,%s,\"%s\",\"%s\",%s)";
			sql = String.format(sql,account,
					rootid,
					String.valueOf(pid),title,content,"NOW()");
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}