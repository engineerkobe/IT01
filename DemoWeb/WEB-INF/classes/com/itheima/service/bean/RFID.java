package com.itheima.service.bean;

import java.util.Date;

public class RFID {
	/*
	 * +----------------+--------------+------+-----+---------+-------+
| Field          | Type         | Null | Key | Default | Extra |
+----------------+--------------+------+-----+---------+-------+
| rfid           | varchar(255) | NO   | PRI | NULL    |       |
| is_borrow      | varchar(1)   | NO   |     | NULL    |       |
| borrower       | varchar(255) | NO   |     | NULL    |       |
| log            | datetime     | NO   |     | NULL    |       |
| isbn           | varchar(255) | NO   | MUL | NULL    |       |
| book_case_name | text         | YES  |     | NULL    |       |
+----------------+--------------+------+-----+---------+-------+
	 */
	private String rfid;
	private String borrower;
	private Date log;
	private String isbn;
	private String isBorrow;
	private String book_case_name;
	
	
	public String getIsBorrow() {
		return isBorrow;
	}
	public void setIsBorrow(String isBorrow) {
		this.isBorrow = isBorrow;
	}
	
	
	
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public String getBorrower() {
		return borrower;
	}
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	public Date getLog() {
		return log;
	}
	public void setLog(Date log) {
		this.log = log;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBook_case_name() {
		return book_case_name;
	}
	public void setBook_case_name(String book_case_name) {
		this.book_case_name = book_case_name;
	}
	
}
