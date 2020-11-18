package com.bank.dao;

import java.sql.SQLException;
import java.util.List;

import com.bank.beans.Accounts;

public interface AccountsDao {
	
		public boolean amountValidation(Accounts a, double amount) throws SQLException;
		//new account
		public int newAccount(Accounts a) throws SQLException;
		//withdraw
		public void withdraw(Accounts a, double amount) throws SQLException;
		//deposit
		public void deposit(Accounts a, double amount) throws SQLException;
		//deleteAccount
		public void deleteAccount(Accounts a) throws SQLException;
		// get account by id
		public Accounts getAccountById(int id) throws SQLException;
		
		public void setStatus(Accounts a, boolean b) throws SQLException;
		
		public List<Accounts> viewAllAccounts() throws SQLException;
}
