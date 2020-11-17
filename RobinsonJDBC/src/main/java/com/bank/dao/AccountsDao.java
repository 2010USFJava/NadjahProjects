package com.bank.dao;

import java.sql.SQLException;

import com.bank.beans.Accounts;

public interface AccountsDao {
	
		public boolean amountValidation(Accounts a, double amount) throws SQLException;
		//new account
		public void newAccount(Accounts a) throws SQLException;
		//withdraw
		public void withdraw(Accounts a, double amount) throws SQLException;
		//deposit
		public void deposit(Accounts a, double amount) throws SQLException;
		//deleteAccount
		public void deleteAccount(Accounts a) throws SQLException;
		// get account by id
		public Accounts getAccountById(int id) throws SQLException;
		
		public void updateAccount(int id) throws SQLException;
}
