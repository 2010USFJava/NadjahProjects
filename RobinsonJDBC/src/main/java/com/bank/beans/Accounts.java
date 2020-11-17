package com.bank.beans;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Accounts {
	private int accountId;
	private BigDecimal balance;
	private Date acct_opened;
	private boolean active;
	
	public Accounts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Accounts(int accountId, Date acct_opened, double balance, boolean active) {
		super();
		LocalDate l = LocalDate.now();
		this.accountId = accountId;
		this.balance = BigDecimal.valueOf(balance);
		this.acct_opened =  java.sql.Date.valueOf(l);
		this.active = active;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public Date getAcct_opened() {
		return acct_opened;
	}
	public void setAcct_opened(Date acct_opened) {
		this.acct_opened = acct_opened;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "Accounts [accountId=" + accountId + ", balance=" + balance + ", acct_opened=" + acct_opened
				+ ", active=" + active + "]";
	}
	
	
}
