package com.itheima.service.bean;

public class UserInfo {
	private String Account;
	private String mName;
	private String mHeadSticker;
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccount() {
		return Account;
	}
	public void setAccount(String account) {
		Account = account;
	}
	
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmHeadSticker() {
		return mHeadSticker;
	}
	public void setmHeadSticker(String mHeadSticker) {
		this.mHeadSticker = mHeadSticker;
	}
}
