package com.bank.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bank.beans.Accounts;
import com.bank.beans.Users;
import com.bank.dao.User_AccountsDao;
import com.bank.util.ConnFactory;

public class User_AccountsDaoImpl implements User_AccountsDao {
	public static ConnFactory cf = ConnFactory.getInstance();
	
	@Override
	public List<Accounts> getAccountByUserId(int id) throws SQLException {
		List<Accounts> acctsList = new ArrayList<Accounts>();
		Connection conn = cf.getConnection();
		String sql ="select * from user_accounts where userId=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		int a = 0;
		
		Accounts b;
		while(rs.next()) {
			a = rs.getInt(2); 
			String sql1 ="select * from accounts where accountId=?";
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ps1.setInt(1, a);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()) {
				String thisDate = rs1.getString(2);
				System.out.println(thisDate);
				b = new Accounts(rs1.getInt(1), rs1.getDate(2), rs1.getDouble(3), rs1.getBoolean(4));
				acctsList.add(b);
			}
		}
		//System.out.println(usersList.toString());
		return acctsList;
	}

	@Override
	public List<Users> getUserByAccountId(int id) throws SQLException {
		List<Users> usersList = new ArrayList<Users>();
		Connection conn = cf.getConnection();
		String sql ="select * from user_accounts where accountId=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		int a = 0;
		
		Users b;
		while(rs.next()) {
			a = rs.getInt(1); 
			String sql1 ="select * from users where userId=?";
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ps1.setInt(1, a);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()) {
				b = new Users(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5), rs1.getBoolean(6));
				usersList.add(b);
			}
		}
		//System.out.println(usersList.toString());
		return usersList;
	}
		
		

	@Override
	public void newRelation(int a, int b) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "insert into user_accounts values(?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		//index refers to wish question mark in ^^ statement
		//first index is 1
		ps.setInt(1, a);
		ps.setInt(2, b);
		ps.executeUpdate(); //int returned is how many rows were updated
		
		
	}

	@Override
	public void deleteRelation(int a, int b) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "delete from  user_accounts where userId =? and accountId =?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, a);
		ps.setInt(2, b);
		ps.executeUpdate();
		
		System.out.println("Relation Deleted");
	}
	

}
