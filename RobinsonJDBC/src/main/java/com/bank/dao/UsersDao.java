package com.bank.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.bank.beans.Accounts;
import com.bank.beans.Users;


public interface UsersDao {
	
	//insert
	public void newUser(Users u) throws SQLException;
		
	 //retrieve by username
	public Users getUserByUsername(String username) throws SQLException;
	
	
	//view  all users
	public List<Users> viewAllUsers() throws SQLException;
	
	//get userId with username
	public Users getUserById(int id) throws SQLException;

	//update firstName
	public void updateUser(Users a) throws SQLException;
	
	public void deleteUser(Users a) throws SQLException;
	
}
