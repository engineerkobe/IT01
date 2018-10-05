package com.itheima.service.bean;

import java.sql.Blob ;

public class Book {
	private String ISBN;
	private String NAME;
	private String COVER;
	private String BORROWER;
	private String OGR_FID;
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getCOVER() {
		return COVER;
	}
	public void setCOVER(String cOVER) {
		COVER = cOVER;
	}
	public String getBORROWER() {
		return BORROWER;
	}
	public void setBORROWER(String bORROW) {
		BORROWER = bORROW;
	}
	public String getOGR_FID() {
		return OGR_FID;
	}
	public void setOGR_FID(String oGR_FID) {
		OGR_FID = oGR_FID;
	}
	

}
