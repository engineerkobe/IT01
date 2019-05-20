package com.itheima.service.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class Entity {
	public static Book getBook(ResultSet rss) throws SQLException {
//		+---------+--------------+------+-----+---------+-------+
//		| Field   | Type         | Null | Key | Default | Extra |
//		+---------+--------------+------+-----+---------+-------+
//		| isbn    | varchar(255) | NO   | PRI | NULL    |       |
//		| name    | varchar(255) | NO   |     | NULL    |       |
//		| content | text         | NO   |     | NULL    |       |
//		| count   | int(11)      | NO   |     | NULL    |       |
//		+---------+--------------+------+-----+---------+-------+
		Book book = new Book();
		String isbn = rss.getString(rss.findColumn("isbn"));
		String name = rss.getString(rss.findColumn("name"));
		String content = rss.getString(rss.findColumn("content"));
		int count = rss.getInt(rss.findColumn("count"));
		
		book.setContent(content);
		book.setCount(count);
		book.setName(name);
		book.setISBN(isbn);
		return book;
	}
	
	public static RFID getRFID( ResultSet rss) throws SQLException {
//		+----------------+--------------+------+-----+---------+-------+
//		| Field          | Type         | Null | Key | Default | Extra |
//		+----------------+--------------+------+-----+---------+-------+
//		| rfid           | varchar(255) | NO   | PRI | NULL    |       |
//		| is_borrow      | varchar(1)   | NO   |     | NULL    |       |
//		| borrower       | text         | YES  |     | NULL    |       |
//		| return_log     | datetime     | NO   |     | NULL    |       |
//		| isbn           | varchar(255) | NO   | MUL | NULL    |       |
//		| book_case_name | text         | YES  |     | NULL    |       |
//		+----------------+--------------+------+-----+---------+-------+
		RFID rfid = new RFID();
		String isbn = rss.getString(rss.findColumn("isbn"));
		String borrower = rss.getString(rss.findColumn("borrower"));
		String isBorrow = rss.getString(rss.findColumn("is_borrow"));
		Date date = rss.getDate(rss.findColumn("return_log"));
		String book_case_name = rss.getString(rss.findColumn("book_case_name"));
		String rfidid = rss.getString(rss.findColumn("rfid"));
		
		rfid.setIsbn(isbn);
		rfid.setBorrower(borrower);
		rfid.setRfid(rfidid);
		rfid.setLog(date);
		rfid.setBook_case_name(book_case_name);
		rfid.setIsBorrow(isBorrow.equals("1")? "1":"0");
		
		return rfid;
	}

	public static Article getArticle(ResultSet rss) throws SQLException, ParseException {
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
		Article article = new Article();
		String poster = rss.getString(rss.findColumn("poster"));
		int rootid = rss.getInt(rss.findColumn("rootid"));
		int pid = rss.getInt(rss.findColumn("pid"));
		String title = rss.getString(rss.findColumn("title"));
		String content = rss.getString(rss.findColumn("content"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");

		Date pdate = sf.parse(rss.getString("pdate"));
		article.setPoster(poster);
		article.setTitle(title);
		article.setRootid(rootid);
		article.setPid(pid);
		article.setContent(content);
		article.setPdate(pdate);

		return article;
	}

	public static UserInfo getUserInfo( ResultSet rss) throws SQLException {
//		+--------------+--------------+------+-----+---------+-------+
//		| Field        | Type         | Null | Key | Default | Extra |
//		+--------------+--------------+------+-----+---------+-------+
//		| account      | varchar(255) | NO   | PRI | NULL    |       |
//		| password     | varchar(50)  | NO   |     | NULL    |       |
//		| head_sticker | text         | YES  |     | NULL    |       |
//		| name         | varchar(50)  | YES  |     | NULL    |       |
//		+--------------+--------------+------+-----+---------+-------+
		UserInfo userinfo = new UserInfo();
		String account = rss.getString(rss.findColumn("account"));
//		String headSticker = rss.getString(rss.fn。dColumn("head_sticker"));
		String name = rss.getString(rss.findColumn("name"));
		String password = rss.getString(rss.findColumn("password"));
		
		userinfo.setAccount(account);
//		userinfo.setmHeadSticker(headSticker);
		userinfo.setPassword(password);
		userinfo.setmName(name);
		
		return userinfo;
	}
	public static JSONObject UserInfoToJson(UserInfo userinfo) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("account",userinfo.getAccount());
		json.put("name",userinfo.getmName());
		return json;
		
	}
}
