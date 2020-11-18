package com.bank.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bank.beans.Accounts;
import com.bank.beans.UserNotUniqueException;
import com.bank.beans.Users;
import com.bank.dao.UsersDao;
import com.bank.util.ConnFactory;
import com.bank.util.LogThis;

public class UsersDaoImpl implements UsersDao{
	public static ConnFactory cf = ConnFactory.getInstance();
	//might have to put one of these lines in every method
	
	
	
	@Override
	public void newUser(Users u) throws SQLException  {
		Connection conn = cf.getConnection();
		String sql = "insert into users values(default,?,?,?,?,default)";
		PreparedStatement ps = conn.prepareStatement(sql);
		//index refers to wish question mark in ^^ statement
		//first index is 1
		ps.setString(1, u.getFirstname());
		ps.setString(2, u.getLastname());
		ps.setString(3, u.getUsername());
		ps.setString(4, u.getPassword());
		ps.executeUpdate(); //int returned is how many rows were updated
		
	}

	@Override
	public Users getUserByUsername(String username) throws SQLException {
		Connection conn = cf.getConnection();
		String sql= "select * from users where username = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		Users thisUser = null;
		
		if(rs!=null) {
			while(rs.next()) {
				thisUser = new Users(0,rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5), rs.getBoolean(6));
			}
			return thisUser;
		}else {
			return null;
		}
	}

	@Override
	public List<Users> viewAllUsers() throws SQLException {
		List<Users> usersList = new ArrayList<Users>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		//below statement passes the query over to the db
		ResultSet rs = stmt.executeQuery("select * from users");
		Users a = null;
		while(rs.next()) {
			a = new Users(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getBoolean(6)); //corresponds to the column data types
			usersList.add(a);
			
		}
		
		return usersList;
	}


	@Override
	public Users getUserById(int id) throws SQLException {
		Connection conn = cf.getConnection();
		String sql= "select * from users where userId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Users thisUser = null;
		
		if(rs!=null) {
			while(rs.next()) {
				thisUser = new Users(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5), rs.getBoolean(6));
			}
			return thisUser;
		}else {
			return null;
		}
	} 

	@Override
	public void updateUser(Users a) throws SQLException {
		//catch and recall in menu
		//in getbyUsername catch null pointer
		int thisId = a.getUserId();
		Scanner nscan = new Scanner(System.in);
		System.out.println("Enter new values for User with id" + a.getUserId());
		System.out.println("Enter first name. Old firstname: " +  a.getFirstname());
		String newFName = nscan.nextLine();
		System.out.println("Enter last name. Old lastname: " +  a.getLastname());
		String newLName = nscan.nextLine();
		System.out.println("Enter username. Old username: " +  a.getUsername());
		String newUName = nscan.nextLine();
		System.out.println("Enter password. Old password: " +  a.getPassword());
		String newPass = nscan.nextLine();
		System.out.println("Enter Admin Status (true/false). Current Admin Staus: " +  a.isAdmin());
		Boolean newStatus = nscan.nextBoolean();
		System.out.println("Updating user...");
		
		//update
		Connection conn = cf.getConnection();
		String sql= "update users set firstname =?, lastname= ?, username =?, password =?, admin = ?  where userId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, newFName);
		ps.setString(2, newLName);
		ps.setString(3, newUName);
		ps.setString(4, newPass);
		ps.setBoolean(5, newStatus);
		ps.setInt(6, a.getUserId());
		ps.executeUpdate();
		
		int id = a.getUserId();
		LogThis.LogIt("info", "User " + id + " successfully updated.");
	}

	@Override
	public void deleteUser(Users a) throws SQLException {
		
			Connection conn = cf.getConnection();
			String sql= "delete from users where userId = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, a.getUserId());
				ps.executeUpdate();
				System.out.println("Account was deleted");
				
		int id = a.getUserId();
		LogThis.LogIt("info", "User " + id + " successfully deleted.");	
	}
}
