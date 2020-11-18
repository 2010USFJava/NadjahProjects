package com.bank.beans;

public class User_Accounts {
	private int userId;
	private int accountId;
	public User_Accounts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User_Accounts(int userId, int accountId) {
		super();
		this.userId = userId;
		this.accountId = accountId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	@Override
	public String toString() {
		return "\n User_Accounts [userId=" + userId + ", accountId=" + accountId + "]";
	}
	
	
}
