package com.bank.impl;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Scanner;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bank.beans.Accounts;
//import com.bank.beans.Users;
import com.bank.dao.AccountsDao;
import com.bank.util.ConnFactory;
import com.bank.util.LogThis;

public class AccountsDaoImpl implements AccountsDao{
	public static ConnFactory cf = ConnFactory.getInstance();
	
	@Override
	public void deposit(Accounts a, double amount) throws SQLException {
		Connection conn = cf.getConnection();
		
		BigDecimal newBalance = a.getBalance();
		newBalance = newBalance.add(BigDecimal.valueOf(amount));
		a.setBalance(newBalance);
		String sql= "update accounts set balance = ? where accountId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(2, a.getAccountId());
		ps.setBigDecimal(1, newBalance);
		ps.executeUpdate();
		
		int id = a.getAccountId();
		LogThis.LogIt("info", "Account " + id + " successfully deposited " + amount);
	}

	@Override
	public void deleteAccount(Accounts a) throws SQLException {
		Connection conn = cf.getConnection();
		String sql= "delete from accounts where accountId = ?";
		if(a.getBalance().doubleValue() <= 0.0 ) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getAccountId());
			ps.executeUpdate();
			System.out.println("Account was deleted");
		}else {
			System.out.println("Only accounts with 0.00 balance may be deleted");
		}
		int id = a.getAccountId();
		LogThis.LogIt("info", "Account " + id + " successfully deleted");
	}

	@Override
	public void withdraw(Accounts a, double amount) throws SQLException {
		Connection conn = cf.getConnection();
		
		BigDecimal newBalance = a.getBalance();
		newBalance = newBalance.subtract(BigDecimal.valueOf(amount));
		a.setBalance(newBalance);
		String sql= "update accounts set balance = ? where accountId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setBigDecimal(1, newBalance);
		ps.setInt(2, a.getAccountId());
		ps.executeUpdate();
		
	}
	@Override
	//this goes in an if in the menus
	public boolean amountValidation(Accounts a, double amount) {
		boolean accept = false;
		if (amount > a.getBalance().floatValue()) {
			System.out.println("Insuficient Funds: Requested amount is greater than available balance.");
			return accept;
		} else if (amount <= 0.00) {
			System.out.println("Invalid Amount: Please enter a non-zero non-negative number");
			return accept;
		} else {
			accept=true;
			return accept;
		}
	}

	@Override
	public int newAccount(Accounts a) throws SQLException {
		int acctId = 0;
		Connection conn = cf.getConnection();
		String sql = "insert into accounts values(default,?,?,default)";
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		//index refers to wish question mark in ^^ statement
		//first index is 1
		ps.setDate(1, a.getAcct_opened());
		ps.setBigDecimal(2, a.getBalance());
		int s = ps.executeUpdate(); //int returned is how many rows were updated
		if(s>0) {
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				acctId = rs.getInt(1);
			}
		}
		//TODO log this
		return acctId;
		
	}

	@Override
	public Accounts getAccountById(int id) throws SQLException {
		Connection conn = cf.getConnection();
		String sql= "select * from accounts where accountId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Accounts thisA = null;
		
		if(rs!=null) {
			while(rs.next()) {
				thisA = new Accounts(rs.getInt(1), rs.getDate(2), rs.getDouble(3), rs.getBoolean(4));
			}
			return thisA;
		}else {
			return null;
		}

		
	}
	@Override
	public List<Accounts> viewAllAccounts() throws SQLException {
		List<Accounts> accList = new ArrayList<Accounts>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		//below statement passes the query over to the db
		ResultSet rs = stmt.executeQuery("select * from accounts");
		Accounts a = null;
		while(rs.next()) {
			a = new Accounts(rs.getInt(1), rs.getDate(2), rs.getDouble(3), rs.getBoolean(4)); //corresponds to the column data types
			accList.add(a);
			
		}
		
		return accList;
	}

	@Override
	public void setStatus(Accounts a, boolean b ) throws SQLException {
		Connection conn = cf.getConnection();
		String sql= "update accounts set  active = ?  where accountId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setBoolean(1, b);
		ps.setInt(2, a.getAccountId());
		ps.executeUpdate();
		//TODO log
	}

	
}
