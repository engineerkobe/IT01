package com.itheima.service.bean;

import java.sql.Blob ;

public class Book {
	/*
	 *  +---------+--------------+------+-----+---------+-------+
		| Field   | Type         | Null | Key | Default | Extra |
		+---------+--------------+------+-----+---------+-------+
		| isbn    | varchar(255) | NO   | PRI | NULL    |       |
		| name    | varchar(255) | NO   |     | NULL    |       |
		| content | text         | NO   |     | NULL    |       |
		| count   | int(11)      | NO   |     | NULL    |       |
		+---------+--------------+------+-----+---------+-------+
	 */
	private String ISBN;
	private String Name;
	private String Content;
	private int Count;
	
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	

}
