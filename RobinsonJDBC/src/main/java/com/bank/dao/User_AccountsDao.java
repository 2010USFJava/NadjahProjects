package com.bank.dao;

import java.sql.SQLException;
import java.util.List;

import com.bank.beans.Accounts;
import com.bank.beans.Users;

public interface User_AccountsDao {
	
	//insert row
	public void newRelation(int a, int b) throws SQLException;
	//get account by userid
	public  List<Accounts> getAccountByUserId(int id) throws SQLException;
	//get user by account id
	public  List<Users> getUserByAccountId(int id) throws SQLException;
	//delete relation
	public void deleteRelation(int a, int b) throws SQLException;
}
