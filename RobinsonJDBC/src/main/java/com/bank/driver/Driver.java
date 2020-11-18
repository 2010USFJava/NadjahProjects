package com.bank.driver;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.bank.beans.Accounts;
import com.bank.beans.Users;
import com.bank.dao.AccountsDao;
import com.bank.dao.User_AccountsDao;
import com.bank.dao.UsersDao;
import com.bank.impl.AccountsDaoImpl;
import com.bank.impl.User_AccountsDaoImpl;
import com.bank.impl.UsersDaoImpl;
import com.bank.menu.Menu;

public class Driver {

	public static void main(String[] args) {
		//create new user test
		AccountsDao newA = new AccountsDaoImpl(); 
		User_AccountsDao newUA = new User_AccountsDaoImpl();
		UsersDao newU = new UsersDaoImpl(); 
		
		
		Menu.mainMenu();
	}

}
