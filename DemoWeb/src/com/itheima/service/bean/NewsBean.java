package com.itheima.service.bean;

public class NewsBean {
	private int BOOK_ID;
	private String BOOK_NAME;
	private String WRITER;
	private String OUTPUT;
	private String PRICE;
	
	public int getBOOK_ID() {
		return BOOK_ID;
	}
	public void setBOOK_ID(int bOOK_ID) {
		BOOK_ID = bOOK_ID;
	}
	public String getBOOK_NAME() {
		return BOOK_NAME;
	}
	public void setBOOK_NAME(String bOOK_NAME) {
		BOOK_NAME = bOOK_NAME;
	}
	public String getWRITER() {
		return WRITER;
	}
	public void setWRITER(String wRITER) {
		WRITER = wRITER;
	}
	public String getOUTPUT() {
		return OUTPUT;
	}
	public void setOUTPUT(String oUTPUT) {
		OUTPUT = oUTPUT;
	}
	public String getPRICE() {
		return PRICE;
	}
	public void setPRICE(String pRICE) {
		PRICE = pRICE;
	}
}
